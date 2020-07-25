package ru.j4jdraft.mt.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.collections.list.*;

import java.util.Iterator;

@ThreadSafe
public class ThreadSafeArrayList<E> implements Iterable<E> {
    @GuardedBy("this")
    private final SimpleArrayList<E> list = new SimpleArrayList<>();

    /**
     * Adds the specified element to the end of the list.
     * @param value element to add
     */
    public synchronized void add(E value) {
        list.add(value);
    }

    /**
     * Gets value at the specified index.
     * @param index position of the value in the list
     * @return value at the position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public synchronized E get(int index) throws IndexOutOfBoundsException {
        return list.get(index);
    }

    /**
     * Returns iterator that uses snapshot of elements in the list
     * at the moment of invoking this method.
     * It allows use this list concurrently and iterate
     * without <code>ConcurrentModificationException</code>.
     * @return fail-save iterator over the elements in this list
     */
    @Override
    public synchronized Iterator<E> iterator() {
        return snapShot().iterator();
    }

    private Iterable<E> snapShot() {
        SimpleArrayList<E> copiedList = new SimpleArrayList<>();
        list.iterator().forEachRemaining(copiedList::add);
        return copiedList;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
