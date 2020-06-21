package ru.j4jdraft.ood.mnpoly;

import java.util.List;

public interface Owner {
    void take(List<BankNote> bankNotes);

    List<BankNote> give(int value);

    List<BankNote> buyAssets(List<Asset> assets);

    List<Asset> sellAssets(List<BankNote> bankNotes, Class<? extends Asset> clazz, int amount);
}
