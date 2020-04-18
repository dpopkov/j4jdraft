package ru.j4jdraft.ood.tictac.model;

public interface Player {
    PlayerId getPlayerId();
    Position makeMove(Grid grid);
}
