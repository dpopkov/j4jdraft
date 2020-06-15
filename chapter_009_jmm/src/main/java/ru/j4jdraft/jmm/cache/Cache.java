package ru.j4jdraft.jmm.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Кэш на основе SoftReferences.
 * Значения хранящиеся в кэшэ могут быть утилизированы сборщиком мусора
 * в случае недостатка памяти.
 * @param <K> type of key
 * @param <V> type of value
 */
public class Cache <K, V> {
    /**
     * Загружает значение в кэш, если его там нет.
     */
    private Function<K, V> provider;

    private final Map<K, SoftReference<V>> map = new HashMap<>();

    public Cache(Function<K, V> provider) {
        this.provider = provider;
    }

    public void setProvider(Function<K, V> provider) {
        this.provider = provider;
    }

    public V get(K key) {
        SoftReference<V> reference = map.get(key);
        V value;
        if (reference == null) {
            value = provideCachedValue(key);
        } else {
            value = reference.get();
            if (value == null) {
                value = provideCachedValue(key);
            }
        }
        return value;
    }

    private V provideCachedValue(K key) {
        V value = provider.apply(key);
        map.put(key, new SoftReference<>(value));
        return value;
    }
}
