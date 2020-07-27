package ru.j4jdraft.mt.waitnotify;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.LongAdder;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class CountBarrierTest {

    @Test
    public void whenAwaitingThenResumeOnlyWhenCountedDown() throws InterruptedException {
        final int numCounts = 3;
        CountBarrier barrier = new CountBarrier(numCounts);
        AtomicBoolean firstPassed = new AtomicBoolean(false);
        AtomicBoolean secondPassed = new AtomicBoolean(false);
        LongAdder adder = new LongAdder();
        Thread firstAwaiting = new Thread(() -> {
            barrier.await();
            if (adder.longValue() == numCounts) {
                firstPassed.set(true);
            }
        });
        Thread secondAwaiting = new Thread(() -> {
            barrier.await();
            if (adder.longValue() == numCounts) {
                secondPassed.set(true);
            }
        });
        firstAwaiting.start();
        secondAwaiting.start();
        Runnable countingTask = () -> {
            adder.increment();
            barrier.count();
        };
        for (int i = 0; i < numCounts; i++) {
            new Thread(countingTask).start();
        }
        firstAwaiting.join(100);
        secondAwaiting.join(100);
        assertThat(firstPassed.get(), is(true));
        assertThat(secondPassed.get(), is(true));
    }
}
