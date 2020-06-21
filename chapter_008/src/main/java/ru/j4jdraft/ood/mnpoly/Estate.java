package ru.j4jdraft.ood.mnpoly;

import java.util.ArrayList;
import java.util.List;

public class Estate implements Asset {
    private EstateGroup group;
    private String name;
    private boolean mortgaged;
    private int price;
    private Owner owner;
    private List<Asset> assets = new ArrayList<>();

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
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public int price() {
        return price;
    }
}
