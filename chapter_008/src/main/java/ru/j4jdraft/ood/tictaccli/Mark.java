package ru.j4jdraft.ood.tictaccli;

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
