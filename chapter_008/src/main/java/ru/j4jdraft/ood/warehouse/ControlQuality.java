package ru.j4jdraft.ood.warehouse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Распределяет продукты по приемникам в зависимости от состояния.
 */
public class ControlQuality {
    private final Map<StoreCycle, FoodConsumer> destinations = new HashMap<>();
    private StoreCycleCalculator stateCalculator;

    public ControlQuality(StoreCycleCalculator stateCalculator) {
        this.stateCalculator = stateCalculator;
    }

    // todo: how to encapsulate foodAction -- make Strategy than encapsulates state and action
    public void addDestination(StoreCycle state, FoodConsumer destination, FoodConsumer foodAction) {
        destinations.put(state, destination);
    }

    // todo: test change of strategy
    public void setStateCalculator(StoreCycleCalculator stateCalculator) {
        this.stateCalculator = stateCalculator;
    }

    public void sort(List<Food> foodList) {
        for (Food food : foodList) {
            StoreCycle foodState = stateCalculator.calculate(food);
            FoodConsumer consumer = destinations.get(foodState);
            consumer.accept(food);
        }
    }

    // todo: учесть будущее "Динамическое перераспределение продуктов [#854]"
    /* извлекать все продукты и перераспределять их заново. */
    /*public void reSort() {

    }*/
}
