package ru.j4jdraft.ood.tictaccli;

/**
 * Represents input for game.
 */
public interface Input {
    /** Requests position for the next move using the specified prompt. */
    Position requestPosition(String prompt);
}
