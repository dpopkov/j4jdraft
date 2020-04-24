package ru.j4jdraft.ood.tictac.model;

public interface Config {
    String uiType();
    int gridSize();
    int getStartingId();
    int getWinLineLength();
    boolean isInitialized();
}
