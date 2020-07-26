package ru.j4jdraft.mt.notification;

public class Closer extends RandomizedActor {
    public Closer(Latch latch, int timeoutBound, int numActions, boolean verbose) {
        super("Closer", () -> {
            if (verbose) {
                System.out.println(Thread.currentThread().getName() + " tries to close latch");
            }
            latch.close();
            return null;
        }, timeoutBound, numActions, verbose);
    }
}
