package ru.j4jdraft.ood.mnpoly;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int position;
    private List<BankNote> bankNotes;
    private final List<TitleDeed> titleDeeds = new ArrayList<>();

    // The players are given $1500
    public Player(List<BankNote> bankNotes) {
        this.bankNotes = bankNotes;
    }

    public void take(List<BankNote> bankNotes) {

    }

    public List<BankNote> give(int value) {
        return null;
    }

    public int position() {
        return -1;
    }

    public void keepCard(ChanceCard card) {

    }
}
