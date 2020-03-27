package ru.j4jdraft.ood.tictac.model;

public interface GameGrid extends Grid {
    void setMark(int row, int col, Mark mark);
}
