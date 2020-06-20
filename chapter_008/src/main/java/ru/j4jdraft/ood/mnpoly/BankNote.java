package ru.j4jdraft.ood.mnpoly;

public enum BankNote {
    ONE(1),
    FIVE(5),
    TEN(10),
    TWENTY(20),
    FIFTY(50),
    HUNDRED(100),
    FIVE_HUNDRED(500);

    private final int value;

    BankNote(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
