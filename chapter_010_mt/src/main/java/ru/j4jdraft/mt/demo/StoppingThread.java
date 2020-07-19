package ru.j4jdraft.mt.demo;

import java.util.concurrent.TimeUnit;

/**
 * Demonstrates how a thread can finish if interrupted.
 */
public class StoppingThread {
    public static void main(String[] args) throws InterruptedException {
        var stoppable = new Thread(() -> {
            while (!Thread.interrupted()) {
                System.out.println(Thread.currentThread().getName() + " is still running");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " is interrupted");
                    Thread.currentThread().interrupt();
                }
            }
        }, "Stoppable Thread");
        stoppable.start();
        TimeUnit.SECONDS.sleep(3);
        stoppable.interrupt();
    }
}
