package ru.j4jdraft.ood.warehouse;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Food> foods = new ArrayList<>();

        StoreCycleCalculator calculator = new SimpleStoreCycleCalculator(25, 75);

        ControlQuality control = new ControlQuality(calculator);
        control.sort(foods);
    }
}
