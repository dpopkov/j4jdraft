package ru.j4jdraft.mt.demo;

public class ThreadStop {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int count = 0;
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(count++);
            }
        });
        thread.start();
        Thread.sleep(1L);
        thread.interrupt();
    }
}
