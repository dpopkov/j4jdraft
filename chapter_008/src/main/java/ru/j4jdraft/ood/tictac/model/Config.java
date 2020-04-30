package ru.j4jdraft.ood.tictac.model;

public interface Config {
    /** Returns type of user interface. */
    String uiType();

    /** Returns size of the square grid. */
    int gridSize();

    /** Returns id of the player that should start the game. */
    int getStartingId();

    /**
     * Returns number of adjacent marks that a winner
     * should place in horizontal, vertical, or diagonal row.
     */
    int getWinLineLength();

    /** Returns true if this config is initialized. */
    boolean isInitialized();
}
