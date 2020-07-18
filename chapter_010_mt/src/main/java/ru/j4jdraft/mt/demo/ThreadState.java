package ru.j4jdraft.mt.demo;

/**
 * Creates two threads, prints their names and states
 * until the threads are finished.
 */
public class ThreadState {
    public static void main(String[] args) {
        DemoThread first = new DemoThread("First");
        DemoThread second = new DemoThread("Second");
        first.showState();
        second.showState();
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED
                || second.getState() != Thread.State.TERMINATED) {
            first.showState();
            second.showState();
        }
        first.showState();
        second.showState();
    }

    private static class DemoThread extends Thread {
        public DemoThread(String name) {
            super(name);
        }

        public void showState() {
            System.out.println(getName() + " is " + getState());
        }

        @Override
        public void run() {
            // does nothing
        }
    }
}
