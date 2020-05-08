package ru.j4jdraft.ood.tictaccli;

/**
 * Represents configuration of the application.
 */
public interface Config {
    /** Type of player that is used in configuration. */
    enum PlayerType {
        HUMAN,
        COMPUTER
    }
    /** Returns size of the grid. */
    int getGridSize();
    /** Returns type of the player that starts the game. */
    PlayerType getFirstPlayer();
    /** Returns number of marks in line that wins the game. */
    int getWinLineLength();
    /** Returns delay in milliseconds used by computer player. */
    long getAnswerDelay();
}
