package ru.j4jdraft.mt.waitnotify;

import net.jcip.annotations.ThreadSafe;

/**
 * A synchronization counting barrier that allows one or more threads
 * to wait until a specified amount of counts occurs.
 */
@ThreadSafe
public class CountBarrier {
    private final Object monitor = this;
    private final int total;
    private int count;

    public CountBarrier(int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            count++;
            if (count == total) {
                monitor.notifyAll();
            }
        }
    }

    public void await() {
        synchronized (monitor) {
            while (count < total) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
