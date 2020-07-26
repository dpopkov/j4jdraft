package ru.j4jdraft.mt.notification;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Latch {
    @GuardedBy("this")
    private boolean open;

    public Latch(boolean open) {
        this.open = open;
    }

    public synchronized void open() throws InterruptedException {
        while (open) {
            wait();
        }
        open = true;
        System.out.println(Thread.currentThread().getName() + " opened latch");
        notifyAll();
    }

    public synchronized void close() throws InterruptedException {
        while (!open) {
            wait();
        }
        open = false;
        System.out.println(Thread.currentThread().getName() + " closed latch");
        notifyAll();
    }
}
