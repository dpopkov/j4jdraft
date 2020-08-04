package ru.j4jdraft.mt.switcher;

/**
 * Provides synchronization between two alternating threads - First and Second.
 */
public class FirstSecondBarrier {
    private boolean nowFirst = true;

    /** Waits until the first thread is allowed to do its work. */
    public synchronized void tryFirst() throws InterruptedException {
        while (!nowFirst) {
            wait();
        }
    }

    /** Waits until the second thread is allowed to do its work. */
    public synchronized void trySecond() throws InterruptedException {
        while (nowFirst) {
            wait();
        }
    }

    /** Signals that the first thread did its part. */
    public synchronized void doneFirst() {
        nowFirst = false;
        notifyAll();
    }

    /** Signals that the second thread did its part. */
    public synchronized void doneSecond() {
        nowFirst = true;
        notifyAll();
    }
}
