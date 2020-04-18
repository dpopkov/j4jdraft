package ru.j4jdraft.ood.tictac.model;

public interface Grid {
    Mark getMark(Position position);
    boolean isFreeAt(Position position);
    int size();
}
