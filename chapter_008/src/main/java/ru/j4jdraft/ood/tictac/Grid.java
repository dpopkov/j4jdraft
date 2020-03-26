package ru.j4jdraft.ood.tictac;

public interface Grid {
    Mark getMark(int row, int col);
    boolean isFreeAt(int row, int col);
    int size();
}
