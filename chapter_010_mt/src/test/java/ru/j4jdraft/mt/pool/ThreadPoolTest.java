package ru.j4jdraft.mt.pool;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class ThreadPoolTest {

    @Test
    public void testExecute() throws InterruptedException {
        ThreadPool pool = new ThreadPool(2);
        List<Integer> consumer = Collections.synchronizedList(new ArrayList<>());
        final int numTasks = 3;
        final int numValues = 10;
        for (int i = 0; i < numTasks; i++) {
            pool.execute(() -> IntStream.range(0, numValues).forEach(consumer::add));
        }
        Thread.sleep(5);   // give time for working threads to start
        pool.shutdown();
        pool.awaitTermination();
        int expected = numTasks * numValues;
        assertThat(consumer.size(), is(expected));
    }

    @Test
    public void testShutdownNow() throws InterruptedException {
        ThreadPool pool = new ThreadPool(1);
        final int numTasks = 1;
        AtomicBoolean taskFinished = new AtomicBoolean(false);
        for (int i = 0; i < numTasks; i++) {
            pool.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                    taskFinished.set(true);
                } catch (InterruptedException ignore) {
                    // expected interruption when calling shutdownNow()
                }
            });
        }
        Thread.sleep(5);   // give time for working thread to start
        pool.shutdownNow();
        Thread.sleep(10);
        assertFalse(taskFinished.get());
    }
}
