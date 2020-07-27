package ru.j4jdraft.mt.producerconsumer;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class BoundedBlockingQueueTest {

    @Test
    public void whenPutAndTakeThenReturnsInFirstInFirstOut() {
        BoundedBlockingQueue<Integer> queue = new BoundedBlockingQueue<>(3);
        queue.put(1);
        queue.put(2);
        queue.put(3);
        assertThat(queue.take(), is(1));
        assertThat(queue.take(), is(2));
        assertThat(queue.take(), is(3));
    }

    @Test
    public void whenPutInFullQueueThenWait() throws InterruptedException {
        BoundedBlockingQueue<Integer> queue = new BoundedBlockingQueue<>(1);
        queue.put(11);
        AtomicBoolean afterConsumer = new AtomicBoolean(false);
        AtomicBoolean consumed = new AtomicBoolean(false);
        Thread producer = new Thread(() -> {
            queue.put(22);
            if (consumed.get()) {
                afterConsumer.set(true);
            }
        });
        Thread consumer = new Thread(() -> {
            queue.take();
            consumed.set(true);
        });
        producer.start();
        TimeUnit.MILLISECONDS.sleep(50);
        consumer.start();
        producer.join();
        assertThat(afterConsumer.get(), is(true));
    }

    @Test
    public void whenTakeFromEmptyQueueThenWait() throws InterruptedException {
        BoundedBlockingQueue<Integer> queue = new BoundedBlockingQueue<>(1);
        AtomicBoolean produced = new AtomicBoolean(false);
        AtomicBoolean afterProducer = new AtomicBoolean(false);
        Thread consumer = new Thread(() -> {
            queue.take();
            if (produced.get()) {
                afterProducer.set(true);
            }
        });
        Thread producer = new Thread(() -> {
            queue.put(11);
            produced.set(true);
        });
        consumer.start();
        TimeUnit.MILLISECONDS.sleep(50);
        producer.start();
        consumer.join();
        assertThat(afterProducer.get(), is(true));
    }
}
