package ru.j4jdraft.ood.tictaccli;

/**
 * Represents player participating in the game.
 */
public interface Player {
    /**
     * Returns position for the next move in the game using the specified grid view
     * or null if the grid is full.
     */
    Position makeMove(GridView view);

    /** Returns mark used by this player. */
    Mark getMark();
}
