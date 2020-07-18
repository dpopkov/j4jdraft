package ru.j4jdraft.mt.demo;

import java.util.concurrent.TimeUnit;

/**
 * Prints rotating bar symbols in cycle in secondary thread.
 * Then interrupts the secondary thread after specified timeout.
 */
public class ConsoleProgress implements Runnable {
    private static final char[] SYMBOLS = {'-', '\\', '|', '/'};

    private final long delay;

    public ConsoleProgress(long delay) {
        this.delay = delay;
    }

    @Override
    public void run() {
        int i = 0;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Loading ... " + SYMBOLS[i++]);
                i = i % SYMBOLS.length;
                TimeUnit.MILLISECONDS.sleep(delay);
            }
        } catch (InterruptedException e) {
            System.out.println("ConsoleProgress interrupted");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long duration = 2000L;
        long progressDelay = 500L;
        if (args.length > 0) {
            duration = Long.parseLong(args[0]);
        }
        if (args.length > 1) {
            progressDelay = Long.parseLong(args[1]);
        }
        Thread progress = new Thread(new ConsoleProgress(progressDelay));
        progress.start();
        Thread.sleep(duration);
        progress.interrupt();
    }
}
