package ru.j4jdraft.collections.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node<E> {
    private final E value;
    private final List<Node<E>> children = new ArrayList<>();

    public Node(E value) {
        this.value = Objects.requireNonNull(value);
    }

    public void add(Node<E> child) {
        children.add(child);
    }

    public E getValue() {
        return value;
    }

    public List<Node<E>> getChildren() {
        return children;
    }

    public boolean eqValue(E that) {
        return this.value.equals(that);
    }
}
