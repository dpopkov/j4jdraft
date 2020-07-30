package ru.j4jdraft.mt.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Simple implementation of stack that uses CAS (Compare and Swap) operation.
 * @param <E> type of element
 */
public class CasStack<E> {
    private final AtomicReference<Node<E>> headRef = new AtomicReference<>();

    public void push(E element) {
        Node<E> newHead = new Node<>(element);
        Node<E> oldHead;
        do {
            oldHead = headRef.get();
            newHead.next = oldHead;
        } while (!headRef.compareAndSet(oldHead, newHead));
    }

    public E pop() {
        Node<E> oldHead;
        Node<E> newHead;
        do {
            oldHead = headRef.get();
            if (oldHead == null) {
                throw new IllegalStateException("Stack is empty");
            }
            newHead = oldHead.next;
        } while (!headRef.compareAndSet(oldHead, newHead));
        oldHead.next = null;
        return oldHead.value;
    }

    public boolean isEmpty() {
        return headRef.get() == null;
    }

    private static class Node<E> {
        private final E value;
        private Node<E> next;

        private Node(E value) {
            this.value = value;
        }
    }
}
