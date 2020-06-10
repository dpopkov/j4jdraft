package ru.j4jdraft.jmm.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

public class Cache {
    private static final Logger LOG = LoggerFactory.getLogger(Cache.class);

    @SuppressWarnings("ConstantConditions")
    public static void main(String[] args) {
        User user = new User(101);
        Reference<User> ref = new SoftReference<>(user);
        System.out.println(user);
        System.out.println("weak: " + ref.get());
        user = null;
        System.gc();
        System.out.println(user);
        System.out.println("weak: " + ref.get());
    }
}
