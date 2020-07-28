package ru.j4jdraft.mt.producerconsumer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class BoundedBlockingQueueTest {

    @Test
    public void whenPutAndTakeThenReturnsInFirstInFirstOut() throws InterruptedException {
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
            try {
                queue.put(22);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (consumed.get()) {
                afterConsumer.set(true);
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (produced.get()) {
                afterProducer.set(true);
            }
        });
        Thread producer = new Thread(() -> {
            try {
                queue.put(11);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            produced.set(true);
        });
        consumer.start();
        TimeUnit.MILLISECONDS.sleep(50);
        producer.start();
        consumer.join();
        assertThat(afterProducer.get(), is(true));
    }

    @Test
    public void whenTakeAllThenReceivesAll() throws InterruptedException {
        List<Integer> buffer = Collections.synchronizedList(new ArrayList<>());
        BoundedBlockingQueue<Integer> queue = new BoundedBlockingQueue<>(2);
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    queue.put(i);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException("Not planning interrupting, so this should no happen");
            }
        }, "Producer");
        Thread consumer = new Thread(() -> {
            while (!(queue.isEmpty() && Thread.currentThread().isInterrupted())) {
                try {
                    buffer.add(queue.take());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Consumer");
        producer.start();
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(List.of(0, 1, 2, 3, 4)));
    }
}
