package ru.j4jdraft.mt.bomberman;

public interface DirectionChooser {
    /** Returns next direction or null if all possible directions were returned. */
    Direction next();
}
