package ru.j4jdraft.mt.demo;

public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread first = new Thread(new AnotherTask());
        Thread second = new Thread(new AnotherTask());
        first.start();
        second.start();
        System.out.println("End of main() in " + Thread.currentThread().getName());
    }

    private static class AnotherTask implements Runnable {
        public AnotherTask() {
            System.out.println("Constructor in " + Thread.currentThread().getName());
        }

        @Override
        public void run() {
            System.out.println("run() in " + Thread.currentThread().getName());
        }
    }
}
