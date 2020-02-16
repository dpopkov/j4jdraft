package ru.j4jdraft.ood.warehouse;

public interface FoodSorter {
    void accept(StoreCycle foodState, Food food);
}
