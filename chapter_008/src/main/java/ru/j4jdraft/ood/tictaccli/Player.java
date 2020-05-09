package ru.j4jdraft.ood.tictaccli;

/**
 * Represents player participating in the game.
 */
public interface Player {
    /** Make the next move in the game using the specified grid view. */
    Position makeMove(GridView view);
    /** Returns mark used by this player. */
    Mark getMark();
}
