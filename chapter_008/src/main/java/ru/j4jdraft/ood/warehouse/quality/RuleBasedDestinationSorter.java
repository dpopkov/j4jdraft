package ru.j4jdraft.ood.warehouse.quality;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Использует правила для сортировки товаров со сроком годности.
 */
public class RuleBasedDestinationSorter<T extends Expirable & Discountable> implements DestinationSorter<T> {
    private final Map<StoreCycle, DestId> destinations = new EnumMap<>(StoreCycle.class);
    private final Map<StoreCycle, Consumer<T>> actions = new EnumMap<>(StoreCycle.class);
    private final StoreCycleCalculator stateCalculator;
    private final Consumer<T> emptyAction = (f) -> {};

    public RuleBasedDestinationSorter(StoreCycleCalculator stateCalculator) {
        this.stateCalculator = stateCalculator;
    }

    public void setRule(StoreCycle state, DestId destinationId, Consumer<T> action) {
        destinations.put(state, destinationId);
        actions.put(state, action);
    }

    public void setRule(StoreCycle state, DestId destinationId) {
        destinations.put(state, destinationId);
    }

    @Override
    public DestId sort(T entity) {
        StoreCycle state = stateCalculator.calculate(entity);
        actions.getOrDefault(state, emptyAction).accept(entity);
        return destinations.get(state);
    }
}
