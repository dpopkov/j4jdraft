package ru.j4jdraft.mt.notification;

public class Opener extends RandomizedActor {
    public Opener(Latch latch, int timeoutBound, int numActions, boolean verbose) {
        super("Opener", () -> {
            if (verbose) {
                System.out.println(Thread.currentThread().getName() + " tries to open latch");
            }
            latch.open();
            return null;
        }, timeoutBound, numActions, verbose);
    }
}
