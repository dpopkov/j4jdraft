package ru.j4jdraft.mt.demo;

/**
 * Demonstrates thread safe double checked lock implementation of Singleton pattern.
 */
public class DclSingleton {
    private static volatile DclSingleton instance;

    private DclSingleton() {
    }

    public static DclSingleton instanceOf() {
        if (instance == null) {
            simulatePauseToAllowOtherThreadEnterThisSection(); // same as in NaiveSingleton
            synchronized (DclSingleton.class) {
                if (instance == null) {
                    instance = new DclSingleton();
                }
            }
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
