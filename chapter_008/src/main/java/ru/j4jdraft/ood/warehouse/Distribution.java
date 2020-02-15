package ru.j4jdraft.ood.warehouse;

public interface Distribution {
    void accept(StoreCycle foodState, Food food);
}
