package ru.j4jdraft.ood.warehouse;

import java.util.ArrayList;
import java.util.List;

public abstract class FoodCollection implements FoodConsumer {
    private List<Food> storedFood = new ArrayList<>();

    @Override
    public void accept(Food food) {
        storedFood.add(food);
    }

    public boolean contains(Food food) {
        return false;
    }
}
