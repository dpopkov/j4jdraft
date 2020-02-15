package ru.j4jdraft.ood.warehouse;

import java.util.Map;

/**
 * Инкапсулирует соответствие между состоянием продукта и местом назначения.
 */
public class WarehouseStopTrash implements Distribution {
    private Map<StoreCycle, FoodConsumer> storageMap;

    public WarehouseStopTrash(Map<StoreCycle, FoodConsumer> storageMap) {
        this.storageMap = storageMap;
    }

    @Override
    public void accept(StoreCycle foodState, Food food) {
        FoodConsumer consumer = storageMap.get(foodState);
        consumer.accept(food);
    }
}
