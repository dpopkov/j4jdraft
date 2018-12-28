package ru.j4jdraft.collections.map;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterable map that uses simple hash table as underlying container.
 * @param <K> type of key object
 * @param <V> type of value object
 */
public class HashMap<K, V> implements Iterable<HashMap.Entry<K, V>> {
    private HashMap.Entry<K, V>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashMap(int initialCapacity) {
        this.table = new HashMap.Entry[initialCapacity];
    }

    /**
     * Inserts and associates the specified key and value.
     * If the hash map contains the key, then returns false.
     * @param key key object
     * @param value value object
     * @return true if the pair value/object inserted, otherwise false
     */
    @SuppressWarnings("unchecked")
    public boolean insert(K key, V value) {
        if (size + 1 > table.length) {
            doubleAndRehash();
        }
        int index = getHash(key) % table.length;
        HashMap.Entry entry = table[index];
        if (entry != null && key.equals(entry.getKey())) {
            return false;
        }
        table[index] = new Entry(key, value);
        size++;
        return true;
    }

    @SuppressWarnings("unchecked")
    private void doubleAndRehash() {
        int newCapacity = table.length == 0 ? 1 : table.length * 2;
        HashMap.Entry<K, V>[] newTable = new HashMap.Entry[newCapacity];
        for (HashMap.Entry e : table) {
            if (e != null) {
                newTable[getHash(e.getKey()) % newTable.length] = e;
            }
        }
        table = newTable;
    }

    /**
     * Gets the value associated with the specified key.
     * @param key key object
     * @return value object or null if the hash map does not contain the key
     */
    public V get(K key) {
        HashMap.Entry<K, V> entry = getValidEntry(key);
        if (entry != null) {
            return entry.getValue();
        }
        return null;
    }

    private HashMap.Entry<K, V> getValidEntry(K key) {
        int idx = getHash(key) % table.length;
        HashMap.Entry<K, V> entry = table[idx];
        if (entry != null && key.equals(entry.getKey())) {
            return entry;
        } else {
            return null;
        }
    }

    /**
     * Deletes the specified key and associated value.
     * @param key key object
     * @return true if key-value pair deleted, otherwise false
     */
    public boolean delete(K key) {
        int idx = getHash(key) % table.length;
        HashMap.Entry<K, V> entry = table[idx];
        if (entry != null && key.equals(entry.getKey())) {
            table[idx] = null;
            size--;
            return true;
        }
        return false;
    }

    private int getHash(Object key) {
        int hash = key == null ? 0 : key.hashCode();
        return hash < 0 ? -hash : hash;
    }

    @Override
    public Iterator<HashMap.Entry<K, V>> iterator() {
        return new HashMapEntryIterator();
    }

    private class HashMapEntryIterator implements Iterator<HashMap.Entry<K, V>> {
        private int index;
        // TODO: ConcurrentModificationException ???

        @Override
        public boolean hasNext() {
            while (index < table.length) {
                if (table[index] != null) {
                    return true;
                }
                index++;
            }
            return false;
        }

        @Override
        public HashMap.Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return table[index++];
        }
    }

    public static class Entry<K, V> {
        private final K key;
        private final V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
