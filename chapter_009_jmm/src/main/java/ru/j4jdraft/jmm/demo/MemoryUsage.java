package ru.j4jdraft.jmm.demo;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class MemoryUsage {

    @SuppressWarnings("UnusedAssignment")
    public static void main(String[] args) throws Exception {
        System.out.println("start");

        /*int numObjects = 64_000;
        if (args.length == 1) {
            numObjects = Integer.parseInt(args[0]);
        }
        for (int i = 0; i < numObjects; i++) {
            new User(i);
            Thread.sleep(1L);
        }*/

        /*User user = new User(101L);
        System.out.println(user);
        user = null;
        System.gc();*/

        MemoryInfo info = new MemoryInfo(1);
        info.print();
        User userObj = new User(101);
        Reference<User> userRef = new WeakReference<>(userObj);
        userObj = null;
        System.out.println("From ref: " + userRef.get());
        System.gc();
        pauseGivingTimeForGC();
        info.print();

        System.out.println("finish");
    }

    private static void pauseGivingTimeForGC() throws InterruptedException {
        Thread.sleep(100L);
    }
}
