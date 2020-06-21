package ru.j4jdraft.ood.mnpoly;

import java.util.ArrayList;
import java.util.List;

public class Estate implements Asset {
    private int group;
    private boolean mortgaged;
    private int price;
    private Owner owner;
    private List<House> houses = new ArrayList<>();
    private Hotel hotel;

    public int calculateRent() {
        return -1;
    }

    public boolean isOwned() {
        return owner == null;
    }

    @Override
    public Owner getOwner() {
        return owner;
    }

    @Override
    public int price() {
        return price;
    }
}
