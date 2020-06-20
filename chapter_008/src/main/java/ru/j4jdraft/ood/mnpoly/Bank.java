package ru.j4jdraft.ood.mnpoly;

import java.util.EnumMap;
import java.util.List;

public class Bank {
    private List<BankNote> bankNotes;
    // the bank also holds the title deed cards
    private List<TitleDeed> titleDeeds;
    // and the houses and hotels prior to player purchase.
    private List<House> houses;
    private List<Hotel> hotels;

    public TitleDeed sellTitleDeed(Property boughtProperty, List<BankNote> bankNotes) {
        return null;
    }

    public List<BankNote> changeBankNotes(List<BankNote> bankNotes,
                                          EnumMap<BankNote, Integer> neededAmounts) {
        return null;
    }

    // The bank pays salaries and bonuses.

    // It also sells and auctions properties, while handing out the proper title deed cards.

    // The bank loans money required for mortgages.

    // The bank collects taxes, fines, loans and interests,
    // as well as assess the price of a property.
}
