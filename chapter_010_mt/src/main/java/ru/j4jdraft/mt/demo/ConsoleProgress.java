package ru.j4jdraft.mt.demo;

public class ConsoleProgress implements Runnable {
    private final char[] lastChars = {'-', '\\', '|', '/'};
    private final long delay;
    private int charIdx;

    public ConsoleProgress(long delay) {
        this.delay = delay;
    }

    @SuppressWarnings("BusyWait")
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Loading ... " + lastCharacter());
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println("The thread " + Thread.currentThread().getName()
                        + " interrupted while sleeping");
                break;
            }
        }
    }

    private char lastCharacter() {
        char ch = lastChars[charIdx++];
        charIdx = (charIdx == lastChars.length) ? 0 : charIdx;
        return ch;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress(500L));
        progress.start();
        Thread.sleep(1000L);
        progress.interrupt();
    }
}
