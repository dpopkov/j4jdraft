package ru.j4jdraft.ood.warehouse;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ControlQualityTest {

    @Test
    public void whenShelfLifeLessThan25ThenMoveToWarehouse() {
        // todo: implement
        List<Food> foods = List.of(new Milk(new Date(), new Date(), BigDecimal.valueOf(90)));

    }

    @Test
    public void whenShelfLife25To75ThenMoveToShop() {
        // todo: implement
    }

    @Test
    public void whenShelfLifeGreaterThan75ThenDiscountAndMoveToShop() {
        // todo: implement
    }

    @Test
    public void whenShelfLifeExpiresThenMoveToTrash() {
        // todo: implement
    }

    @Test
    public void whenChangeCalculationStrategyThenChangesDistribution() {
        // todo: implement
    }
}
