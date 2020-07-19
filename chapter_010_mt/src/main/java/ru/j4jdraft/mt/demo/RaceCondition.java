package ru.j4jdraft.mt.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates race condition between threads that try to increment same object {@link Counter}.
 */
public class RaceCondition {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        Counter counter = new Counter();
        int numTasks = 10;
        int numIncrements = 10_000;
        if (args.length > 0) {
            numTasks = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            numIncrements = Integer.parseInt(args[1]);
        }
        Runnable task = new Task(counter, numIncrements);
        for (int i = 0; i < numTasks; i++) {
            Thread t = new Thread(task);
            t.start();
            threads.add(t);
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println("Expected result: " + (numIncrements * numTasks));
        System.out.println("Actual result  : " + counter.getCount());
    }

    private static class Task implements Runnable {
        private final Counter counter;
        private final int numIncrements;

        private Task(Counter counter, int numIncrements) {
            this.counter = counter;
            this.numIncrements = numIncrements;
        }

        @Override
        public void run() {
            for (int i = 0; i < numIncrements; i++) {
                counter.increment();
            }
        }
    }

    private static class Counter {
        private int count;

        public void increment() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }
}
