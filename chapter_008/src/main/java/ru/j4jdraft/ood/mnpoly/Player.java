package ru.j4jdraft.ood.mnpoly;

import ru.j4jdraft.ood.mnpoly.actions.PayToLeaveJailAction;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Player implements Owner {
    private Bank bank;
//    private int position;
    private Space currentSpace;
    private boolean inJail;
    private boolean bankrupt;
    private List<BankNote> money;
    private final List<TitleDeed> titleDeeds = new ArrayList<>();
    private List<Asset> assets = new ArrayList<>();
    private Deque<Action> delayedActions = new ArrayDeque<>();

    // The players are given $1500
    public Player(List<BankNote> money) {
        this.money = money;
    }

    @Override
    public void take(List<BankNote> bankNotes) {

    }

    @Override
    public List<BankNote> give(int value) {
        System.out.println("Prepares banknotes and asks bank to exchange if needed");
        System.out.println("Gives prepared banknotes");
        return null;
    }

    @Override
    public List<BankNote> buyAssets(List<Asset> assets) {
        return null;
    }

    @Override
    public List<Asset> sellAssets(List<BankNote> bankNotes,
                                  Class<? extends Asset> clazz, int amount) {
        return null;
    }

    public void buyEstate(Estate estate) {
        List<BankNote> bankNotes = preparePayment(estate.price());
        TitleDeed titleDeed = bank.sellEstate(estate, bankNotes);
        estate.setOwner(this);
        titleDeeds.add(titleDeed);
    }

    public void moveTo(Space space) {
        performDelayedActions();
        space.enter(this);
    }

    public void pay(Owner receiver, int value) {
        System.out.println("Try to gather enough money to pay the rent");
        System.out.println("Pay the specified value to the owner");
    }

    public void payToBank(int value) {
        System.out.println("Paying the specified value to the bank");
    }

    public boolean isBankrupt() {
        return false;
    }

    public Space getCurrentSpace() {
        return currentSpace;
    }

    public void keepCard(ChanceCard card) {

    }

    public boolean wants(Estate estate) {
        return true;
    }

    private List<BankNote> preparePayment(int value) {
        return null;
    }

    public void allowAuction(Estate estate) {
        bank.auction(estate);
    }

    public void putInJail(Jail jail) {
        jail.putInside(this);
        delayedActions.add(new PayToLeaveJailAction());
        inJail = true;
    }

    private void performDelayedActions() {
        while (!delayedActions.isEmpty()) {
            Action action = delayedActions.remove();
            action.act(this);
        }
    }

    public boolean isInJail() {
        return inJail;
    }

    public void develop(Estate estate) {
        System.out.println("if estate is ready the player can buy and put houses");
    }
}
