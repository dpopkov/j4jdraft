package ru.j4jdraft.ood.tictac.model;

// todo: replace enum with a class so that there could be unlimited number of marks
public enum Mark {
    X(" X"),   // todo: move out the representation of the marks
    O(" O"),
    EMPTY(" .");

    private final String symbol;

    Mark(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
