package ru.j4jdraft.ood.tictac.model;

public enum Mark {
    X("  X"),   // todo: move out the representation of the marks
    O("  O"),
    EMPTY("  .");

    private String symbol;

    Mark(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
