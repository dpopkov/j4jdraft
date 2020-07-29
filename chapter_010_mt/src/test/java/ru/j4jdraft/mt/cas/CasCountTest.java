package ru.j4jdraft.mt.cas;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class CasCountTest {

    @Test
    public void whenIncrementInThreadsThenIncrementsCorrectly() throws InterruptedException {
        CasCount count = new CasCount();
        int numThreads = 5;
        ExecutorService exec = Executors.newFixedThreadPool(numThreads);
        int numIncrements = 200;
        for (int i = 0; i < numThreads; i++) {
            exec.execute(() -> IntStream.range(0, numIncrements).forEach(n -> count.increment()));
        }
        exec.shutdown();
        assertTrue(exec.awaitTermination(100, TimeUnit.MILLISECONDS));
        assertThat(count.get(), is(numThreads * numIncrements));
    }
}
