package ru.j4jdraft.ood.mnpoly.assets;

import ru.j4jdraft.ood.mnpoly.Asset;
import ru.j4jdraft.ood.mnpoly.Owner;

public class House implements Asset {
    @Override
    public int price() {
        return 0;
    }

    @Override
    public Owner getOwner() {
        return null;
    }

    @Override
    public void setOwner(Owner owner) {

    }
}
