package ru.j4jdraft.ood.warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class FoodCollection implements Consumer<Food> {
    private final List<Food> storedFood = new ArrayList<>();
    private final String name;

    public FoodCollection(String name) {
        this.name = name;
    }

    @Override
    public void accept(Food food) {
        storedFood.add(food);
    }

    public boolean contains(Food food) {
        return storedFood.contains(food);
    }

    @Override
    public String toString() {
        return name;
    }
}
