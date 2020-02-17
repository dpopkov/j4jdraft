package ru.j4jdraft.ood.warehouse.foods;

import ru.j4jdraft.ood.warehouse.Food;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Meat extends Food {
    public Meat(LocalDate createDate, LocalDate expireDate, BigDecimal price) {
        super("Meat", createDate, expireDate, price, null);
    }
}
