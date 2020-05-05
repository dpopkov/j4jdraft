package ru.j4jdraft.ood.tictaccli;

public interface Config {
    boolean isInitialized();
    int gridSize();
    int getStartingId();
    int getWinLineLength();
    long getAnswerDelay();
}
