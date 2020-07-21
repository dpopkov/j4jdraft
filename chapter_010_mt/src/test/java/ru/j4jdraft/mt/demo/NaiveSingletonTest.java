package ru.j4jdraft.mt.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class NaiveSingletonTest {

    @Test
    public void createsMoreThanOneSingleton() throws InterruptedException {
        List<NaiveSingleton> refs = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> refs.add(NaiveSingleton.instanceOf()));
            threads.add(t);
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        Set<NaiveSingleton> set = new HashSet<>(refs);
        assertThat(set.size(), is(greaterThan(1)));
    }
}