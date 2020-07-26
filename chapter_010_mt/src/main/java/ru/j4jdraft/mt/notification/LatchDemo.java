package ru.j4jdraft.mt.notification;

import java.util.concurrent.TimeUnit;

public class LatchDemo {
    public static void main(String[] args) throws InterruptedException {
        boolean verbose = false;
        if (args.length > 0) {
            verbose = "verbose".equals(args[0]);
        }
        Latch latch = new Latch(true);
        int timeoutBound = 1000;
        int numActions = 5;
        new Opener(latch, timeoutBound, numActions, verbose);
        TimeUnit.SECONDS.sleep(2);
        new Closer(latch, timeoutBound, numActions, verbose);
    }
}
