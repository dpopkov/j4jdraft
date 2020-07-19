package ru.j4jdraft.mt.demo;

/**
 * Demonstrates synchronizing of common resources.
 */
@SuppressWarnings("unused")
public class Cache {
    private static Cache cache;

    public synchronized static Cache instanceOf() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }

    public void foo() {
        System.out.println("It is not utility class");
    }
}
