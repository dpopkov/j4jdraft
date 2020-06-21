package ru.j4jdraft.ood.mnpoly;

import java.util.List;

public interface Owner {
    void take(List<BankNote> bankNotes);

    List<BankNote> give(int value);
}
