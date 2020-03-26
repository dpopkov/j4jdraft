package ru.j4jdraft.ood.tictac;

public interface GameGrid extends Grid {
    void setMark(int row, int col, Mark mark);
}
