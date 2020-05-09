package ru.j4jdraft.ood.tictaccli;

/**
 * Represents symbols used by players in the game.
 */
public enum Mark {
    X("X"),
    O("O"),
    EMPTY(" ");

    private final String symbol;

    Mark(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
