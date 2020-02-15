package ru.j4jdraft.ood.warehouse;

import java.util.ArrayList;
import java.util.List;

public class FoodStorage {
    private List<Food> storedFood = new ArrayList<>();

    public void accept(Food food) {
        storedFood.add(food);
    }

    public boolean contains(Food food) {
        return false;
    }

    public List<Food> getStoredFood() {
        return storedFood;
    }
}
