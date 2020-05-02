package ru.j4jdraft.ood.tictaccli;

public interface Player {
    Position makeMove(GridView view);
    Mark getMark();
}
