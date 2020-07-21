package ru.j4jdraft.mt.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class DclSingletonTest {

    @Test
    public void createsOnlyOneInstance() throws InterruptedException {
        List<DclSingleton> refs = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> refs.add(DclSingleton.instanceOf()));
            threads.add(t);
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        Set<DclSingleton> set = new HashSet<>(refs);
        assertEquals(1, set.size());
    }
}