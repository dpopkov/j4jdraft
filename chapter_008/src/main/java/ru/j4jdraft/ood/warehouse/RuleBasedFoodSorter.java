package ru.j4jdraft.ood.warehouse;

import java.util.EnumMap;
import java.util.Map;

/**
 * Использует правила для сортировки продуктов.
 */
public class RuleBasedFoodSorter implements FoodSorter {
    private final Map<StoreCycle, DestId> destinations = new EnumMap<>(StoreCycle.class);
    private final Map<StoreCycle, FoodConsumer> actions = new EnumMap<>(StoreCycle.class);
    private final StoreCycleCalculator stateCalculator;
    private final FoodConsumer emptyAction = (f) -> {};

    public RuleBasedFoodSorter(StoreCycleCalculator stateCalculator) {
        this.stateCalculator = stateCalculator;
    }

    public void addRule(StoreCycle state, DestId destinationId, FoodConsumer action) {
        destinations.put(state, destinationId);
        actions.put(state, action);
    }

    public void addRule(StoreCycle state, DestId destinationId) {
        destinations.put(state, destinationId);
    }

    @Override
    public DestId sort(Food food) {
        StoreCycle foodState = stateCalculator.calculate(food);
        actions.getOrDefault(foodState, emptyAction).accept(food);
        return destinations.get(foodState);
    }
}
