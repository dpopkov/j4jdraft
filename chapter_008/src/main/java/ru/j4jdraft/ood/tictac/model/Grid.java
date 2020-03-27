package ru.j4jdraft.ood.tictac.model;

public interface Grid {
    Mark getMark(int row, int col);
    boolean isFreeAt(int row, int col);
    int size();
}
