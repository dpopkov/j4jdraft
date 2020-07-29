package ru.j4jdraft.mt.cas;

import java.util.concurrent.atomic.AtomicReference;

public class CasCount {
    private final AtomicReference<Integer> atomic = new AtomicReference<>(0);

    public void increment() {
        Integer oldValue;
        do {
            oldValue = atomic.get();
        } while (!atomic.compareAndSet(oldValue, oldValue + 1));
    }

    public int get() {
        return atomic.get();
    }
}
