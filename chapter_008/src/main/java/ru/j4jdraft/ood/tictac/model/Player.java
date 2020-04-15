package ru.j4jdraft.ood.tictac.model;

public interface Player {
    int getId();
    Move makeMove(Grid grid);
}
