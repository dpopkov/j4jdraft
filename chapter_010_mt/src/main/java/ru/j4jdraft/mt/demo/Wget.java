package ru.j4jdraft.mt.demo;

/**
 * Simulates loading process and displays current percentage.
 */
public class Wget {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Loaded " + i + "%");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
