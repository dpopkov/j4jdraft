package ru.j4jdraft.mt.demo;

import java.util.concurrent.TimeUnit;

public class ConsoleProgress implements Runnable {
    private final char[] symbols = {'-', '\\', '|', '/'};
    private final long delay;

    public ConsoleProgress(long delay) {
        this.delay = delay;
    }

    @Override
    public void run() {
        int charIdx = 0;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                char symbol = symbols[charIdx++];
                charIdx = charIdx % symbols.length;
                System.out.println("Loading ... " + symbol);
                TimeUnit.MILLISECONDS.sleep(delay);
            }
        } catch (InterruptedException e) {
            System.out.println("ConsoleProgress interrupted");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress(500L));
        progress.start();
        Thread.sleep(2000L);
        progress.interrupt();
    }
}
