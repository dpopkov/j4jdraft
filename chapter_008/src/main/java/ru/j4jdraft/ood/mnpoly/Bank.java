package ru.j4jdraft.ood.mnpoly;

import ru.j4jdraft.ood.mnpoly.assets.Hotel;
import ru.j4jdraft.ood.mnpoly.assets.House;

import java.util.*;

public class Bank implements Owner {
    private List<Player> players = new ArrayList<>();
    private List<BankNote> bankNotes;
    // the bank also holds the title deed cards
    private Map<Estate, TitleDeed> titleDeeds = new HashMap<>();
    // and the houses and hotels prior to player purchase.
    private List<House> houses;
    private List<Hotel> hotels;

    // It also sells and auctions properties, while handing out the proper title deed cards.
    public TitleDeed sellEstate(Estate boughtEstate, List<BankNote> bankNotes) {
        return null;
    }

    public List<BankNote> changeBankNotes(List<BankNote> bankNotes,
                                          EnumMap<BankNote, Integer> neededAmounts) {
        return null;
    }

    @Override
    public void take(List<BankNote> bankNotes) {
        System.out.println("Take banknotes");
    }

    @Override
    public List<BankNote> give(int value) {
        System.out.println("Give banknotes for value");
        return null;
    }

    @Override
    public List<BankNote> buyAssets(List<Asset> assets) {
        return null;
    }

    @Override
    public List<Asset> sellAssets(List<BankNote> bankNotes, Class<? extends Asset> clazz, int amount) {
        return null;
    }

    public List<House> sellHouses(List<BankNote> bankNotes, int amount) {
        return null;
    }

    public Hotel sellHotel(List<BankNote> bankNotes) {
        return null;
    }

    public void auction(Estate estate) {
        System.out.println("Sell this property to the highest bidder");
    }

    // The bank pays salaries and bonuses.

    // The bank loans money required for mortgages.

    // The bank collects taxes, fines, loans and interests,
    // as well as assess the price of a property.
}
