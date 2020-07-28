package ru.j4jdraft.mt.producerconsumer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Simple implementation of bounded blocking queue.
 * @param <E> type of element
 */
@ThreadSafe
public class BoundedBlockingQueue<E> {
    private final int boundedCapacity;
    @GuardedBy("this")
    private final Queue<E> queue;

    public BoundedBlockingQueue(int size) {
        boundedCapacity = size;
        queue = new ArrayDeque<>(size);
    }

    /** Adds the specified element to the queue if the queue is not full,
     * or waits otherwise until consumer of this thread takes element from it.
     * @param element element to add to tail
     */
    public synchronized void put(E element) {
        while (queue.size() == boundedCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        queue.add(element);
        notifyAll();
    }

    /** Removes the element from the head of the queue if the queue is not empty,
     * or waits until producer puts an element to the queue.
     * @return element from the head
     */
    public synchronized E take() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        E e = queue.remove();
        notifyAll();
        return e;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
