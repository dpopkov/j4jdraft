package ru.j4jdraft.mt.demo;

/**
 * Demonstrates incorrect implementation of Singleton pattern which is not thread safe.
 */
public class NaiveSingleton {
    private static volatile NaiveSingleton instance;

    private NaiveSingleton() {
    }

    public static NaiveSingleton instanceOf() {
        if (instance == null) {
            simulatePauseToAllowOtherThreadEnterThisSection();
            instance = new NaiveSingleton();
        }
        return instance;
    }

    private static void simulatePauseToAllowOtherThreadEnterThisSection() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    public void dummyFoo() {
        System.out.println("It is not a utility class");
    }
}
