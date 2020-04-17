package ru.j4jdraft.ood.tictac.model;

public interface Player {
    PlayerId getPlayerId();
    Move makeMove(Grid grid);
}
