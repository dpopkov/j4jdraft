package ru.j4jdraft.mt.notification;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public abstract class RandomizedActor {
    private final Random rand = new Random();

    public RandomizedActor(String name, Callable<Void> action, int timeoutBound, int numActions,
                           boolean verbose) {
        Runnable task = () -> {
            try {
                for (int i = 0; i < numActions; i++) {
                    int timeout = rand.nextInt(timeoutBound);
                    if (verbose) {
                        System.out.println(Thread.currentThread().getName() + " sleeps " + timeout);
                    }
                    TimeUnit.MILLISECONDS.sleep(timeout);
                    action.call();
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        new Thread(task, name).start();
    }
}
