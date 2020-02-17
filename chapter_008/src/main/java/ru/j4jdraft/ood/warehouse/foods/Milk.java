package ru.j4jdraft.ood.warehouse.foods;

import ru.j4jdraft.ood.warehouse.Food;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Milk extends Food {
    public Milk(LocalDate createDate, LocalDate expireDate, BigDecimal price) {
        super("Milk", createDate, expireDate, price, null);
    }
}
