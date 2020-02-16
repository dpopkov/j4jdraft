package ru.j4jdraft.ood.warehouse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Food> foods = new ArrayList<>();

        RuleBasedFoodSorter sorter = new RuleBasedFoodSorter(new SimpleStoreCycleCalculator(25, 75));
        sorter.addRule(StoreCycle.STORAGE, DestId.WAREHOUSE);
        sorter.addRule(StoreCycle.FOR_SALE, DestId.SHOP);
        sorter.addRule(StoreCycle.DISCOUNT_SALE, DestId.SHOP, food -> food.setDiscount(BigDecimal.valueOf(0.10)));
        sorter.addRule(StoreCycle.EXPIRED, DestId.TRASH);

        ControlQuality control = new ControlQuality(sorter);
        control.addDestination(DestId.WAREHOUSE, new Warehouse());
        control.addDestination(DestId.SHOP, new Shop());
        control.addDestination(DestId.TRASH, new Trash());
        control.sort(foods);
    }
}
