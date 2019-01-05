package ru.j4jdraft.collections.tree;

import java.util.*;

public class SimpleTreeImpl<E> implements SimpleTree<E> {
    private final Node<E> root;
    private int modCount;

    public SimpleTreeImpl(E rootValue) {
        this.root = new Node<>(rootValue);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> duplicate = findBy(child);
        if (duplicate.isPresent()) {
            return false;
        }
        Optional<Node<E>> destination = findBy(parent);
        if (destination.isEmpty()) {
            return false;
        }
        destination.get().add(new Node<>(child));
        modCount++;
        return true;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        NodeIterator it = new NodeIterator();
        while (it.hasNext()) {
            Node<E> node = it.next();
            if (node.eqValue(value)) {
                return Optional.of(node);
            }
        }
        return Optional.empty();
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    private class NodeIterator implements Iterator<Node<E>> {
        private final Queue<Node<E>> nodes = new LinkedList<>();
        private final int expectedModCount = modCount;

        private NodeIterator() {
            nodes.add(root);
        }

        @Override
        public boolean hasNext() {
            return !nodes.isEmpty();
        }

        @Override
        public Node<E> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            Node<E> node = nodes.remove();
            nodes.addAll(node.getChildren());
            return node;
        }
    }

    private class ElementIterator implements Iterator<E> {
        private final NodeIterator nodeIterator = new NodeIterator();

        @Override
        public boolean hasNext() {
            return nodeIterator.hasNext();
        }

        @Override
        public E next() {
            return nodeIterator.next().getValue();
        }
    }
}
