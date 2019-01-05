package ru.j4jdraft.collections.tree;

import java.util.Optional;

public interface SimpleTree<E> extends Iterable<E> {
    boolean add(E parent, E child);
    Optional<Node<E>> findBy(E value);
}
