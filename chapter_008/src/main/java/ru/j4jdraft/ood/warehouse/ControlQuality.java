package ru.j4jdraft.ood.warehouse;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Распределяет продукты по приемникам используя внешний алгоритм сортировки продуктов.
 */
public class ControlQuality {
    private final Map<DestId, FoodConsumer> destinations = new EnumMap<>(DestId.class);
    private FoodSorter sortingStrategy;

    public ControlQuality(FoodSorter sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    // todo: test change of strategy
    public void setSortingStrategy(FoodSorter sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    public void addDestination(DestId id, FoodConsumer destination) {
        destinations.put(id, destination);
    }

    public void sort(List<Food> foodList) {
        for (Food food : foodList) {
            DestId id = sortingStrategy.sort(food);
            FoodConsumer consumer = destinations.get(id);
            consumer.accept(food);
        }
    }

    // todo: учесть будущее "Динамическое перераспределение продуктов [#854]"
    /* извлекать все продукты и перераспределять их заново. */
    /*public void reSort() {

    }*/
}
