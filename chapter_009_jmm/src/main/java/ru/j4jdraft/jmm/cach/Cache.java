package ru.j4jdraft.jmm.cach;

import java.lang.ref.SoftReference;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class Cache <K, V> {
    private Function<K, V> provider;

    private Set<SoftReference<K>> softKeys = new HashSet<>();

    public Cache(Function<K, V> provider) {
        this.provider = provider;
    }

    public V get(K key) {
        return provider.apply(key);
    }

    public void setProvider(Function<K, V> provider) {
        this.provider = provider;
    }
}
