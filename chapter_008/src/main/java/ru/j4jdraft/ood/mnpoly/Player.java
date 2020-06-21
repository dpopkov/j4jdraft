package ru.j4jdraft.ood.mnpoly;

import java.util.ArrayList;
import java.util.List;

public class Player implements Owner {
    private Bank bank;
//    private int position;
    private Space currentSpace;
    private List<BankNote> bankNotes;
    private final List<TitleDeed> titleDeeds = new ArrayList<>();
    private List<Asset> assets = new ArrayList<>();

    // The players are given $1500
    public Player(List<BankNote> bankNotes) {
        this.bankNotes = bankNotes;
    }

    @Override
    public void take(List<BankNote> bankNotes) {

    }

    @Override
    public List<BankNote> give(int value) {
        return null;
    }

    public void payRent(Owner owner, int value) {
        System.out.println("Try to gather enough money to pay the rent");
        System.out.println("Pay the specified value to the owner");
    }

    public Space getCurrentSpace() {
        return currentSpace;
    }

    public void keepCard(ChanceCard card) {

    }

    public void buy(Estate estate) {
        List<BankNote> forProperty = preparePayment(estate.price());
        TitleDeed titleDeed = bank.sellEstate(estate, forProperty);
        titleDeeds.add(titleDeed);
    }

    private List<BankNote> preparePayment(int value) {
        return null;
    }

    public void allowAuction(Estate estate) {
        bank.auction(estate);
    }
}
