package ru.j4jdraft.mt.list;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class ThreadSafeArrayListTest {

    @Test
    public void whenAddNewElementThenCanGetIt() {
        ThreadSafeArrayList<Integer> list = new ThreadSafeArrayList<>();
        list.add(42);
        Integer element = list.get(0);
        assertThat(element, is(42));
        list.add(43);
        element = list.get(1);
        assertThat(element, is(43));
    }

    @Test
    public void whenUsingIteratorThenIteratesOverAllElements() {
        ThreadSafeArrayList<Integer> list = new ThreadSafeArrayList<>();
        list.add(11);
        list.add(21);
        list.add(31);
        int i = 11;
        for (Integer value: list) {
            assertThat(value, is(i));
            i += 10;
        }
    }

    @Test
    public void whenAddNewElementsInManyThreadsThenAddsAllElements() throws InterruptedException {
        ThreadSafeArrayList<Integer> list = new ThreadSafeArrayList<>();
        ExecutorService exec = Executors.newCachedThreadPool();
        int numTasks = 5;
        int perTask = 1000;
        for (int i = 0; i < numTasks; i++) {
            exec.execute(() -> {
                for (int j = 0; j < perTask; j++) {
                    list.add(j);
                }
            });
        }
        exec.shutdown();
        boolean allDone = exec.awaitTermination(100, TimeUnit.MILLISECONDS);
        assertTrue(allDone);
        List<Integer> addedElements = new ArrayList<>();
        for (Integer value : list) {
            addedElements.add(value);
        }
        int expectedSize = numTasks * perTask;
        assertThat(addedElements.size(), is(expectedSize));
    }

    @Test
    public void whenIteratingAndAddingInThreadsThenIteratesOverOldValues()
            throws InterruptedException {
        ThreadSafeArrayList<Integer> list = new ThreadSafeArrayList<>();
        for (int i = 0; i < 100; i += 2) {  // add even values
            list.add(i);
        }
        Iterator<Integer> iterator = list.iterator();

        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(() -> {
                for (int j = 1; j < 20; j += 2) {   // add odd values
                    list.add(j);
                }
            });
        }
        exec.execute(() -> assertIteratorReturnsEvenValues(iterator));
        exec.shutdown();
        boolean allDone = exec.awaitTermination(100, TimeUnit.MILLISECONDS);
        assertTrue(allDone);
    }

    private void assertIteratorReturnsEvenValues(Iterator<Integer> iterator) {
        while (iterator.hasNext()) {
            assertEquals(0, iterator.next() % 2);
        }
    }
}
