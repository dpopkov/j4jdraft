package ru.j4jdraft.ood.carparking;

/** Represents parking spot. */
public interface Spot {
    /** Returns size of the spot. */
    int size();

    /** Returns true is the spot is occupied, false otherwise. */
    boolean isOccupied();

    /** Occupies the spot. */
    void occupy();

    /** Set the spot free. */
    void free();
}
