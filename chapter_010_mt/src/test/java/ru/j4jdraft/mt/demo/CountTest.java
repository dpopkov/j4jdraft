package ru.j4jdraft.mt.demo;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class CountTest {

    @Ignore("This test is unnecessary as it tries to test the language feature - 'synchronized'")
    @Test
    public void whenUsedInMultipleThreadsThenGivesCorrectTotalValueOfIncrements()
            throws InterruptedException {
        final int numThreads = 10;
        final int numIncrements = 1000;
        final List<Thread> threads = new ArrayList<>();
        final Count count = new Count();
        for (int i = 0; i < numThreads; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j < numIncrements; j++) {
                    count.increment();
                }
            }));
        }
        threads.forEach(Thread::start);
        for (Thread t : threads) {
            t.join();
        }
        int result = count.getValue();
        int expected = numThreads * numIncrements;
        assertThat(result, is(expected));
    }
}