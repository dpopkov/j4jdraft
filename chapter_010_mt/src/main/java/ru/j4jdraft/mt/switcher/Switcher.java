package ru.j4jdraft.mt.switcher;

import java.util.concurrent.TimeUnit;

public class Switcher {
    public static void main(String[] args) throws InterruptedException {
        int timeoutSeconds = 10;
        if (args.length > 0) {
            timeoutSeconds = Integer.parseInt(args[0]);
        }
        FirstSecondBarrier barrier = new FirstSecondBarrier();
        Runnable firstTask = () -> {
            while (!Thread.interrupted()) {
                try {
                    barrier.tryFirst();
                    System.out.println("First");
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    barrier.doneFirst();
                }
            }
        };
        Runnable secondTask = () -> {
            while (!Thread.interrupted()) {
                try {
                    barrier.trySecond();
                    System.out.println("Second");
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    barrier.doneSecond();
                }
            }
        };
        Thread first = new Thread(firstTask);
        Thread second = new Thread(secondTask);
        second.start();
        first.start();
        TimeUnit.SECONDS.sleep(timeoutSeconds);
        first.interrupt();
        second.interrupt();
    }
}
