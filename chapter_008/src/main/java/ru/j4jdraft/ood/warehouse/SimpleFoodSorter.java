package ru.j4jdraft.ood.warehouse;

import java.util.HashMap;
import java.util.Map;

/**
 * Инкапсулирует соответствие между состоянием продукта и местом назначения.
 * Сортирует и направляет продукт в зависимости от состояния в тот или иной приемник.
 */
public class SimpleFoodSorter implements FoodSorter {
    private Map<StoreCycle, FoodConsumer> destinations = new HashMap<>();

    public void addRule(StoreCycle state, FoodConsumer destination) {
        destinations.put(state, destination);
    }

    @Override
    public void accept(StoreCycle foodState, Food food) {
        FoodConsumer consumer = destinations.get(foodState);
        consumer.accept(food);
    }
}
