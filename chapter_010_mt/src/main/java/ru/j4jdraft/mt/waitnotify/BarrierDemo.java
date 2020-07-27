package ru.j4jdraft.mt.waitnotify;

import net.jcip.annotations.ThreadSafe;

@SuppressWarnings("unused")
@ThreadSafe
public class BarrierDemo {
    private final Object monitor = this;
    private boolean flag = false;

    public void on() {
        synchronized (monitor) {
            flag = true;
            System.out.println("On");
            monitor.notifyAll();
        }
    }

    public void off() {
        synchronized (monitor) {
            flag = false;
            System.out.println("Off");
            monitor.notifyAll();
        }
    }

    public void check() {
        synchronized (monitor) {
            System.out.println("Check");
            while (!flag) {
                try {
                    System.out.println("Waiting");
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
