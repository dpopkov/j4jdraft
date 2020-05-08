package ru.j4jdraft.ood.tictaccli;

public interface GridView {
    Mark getMark(Position position);
    boolean isFreeAt(Position position);
    boolean isFull();
    int size();
}
