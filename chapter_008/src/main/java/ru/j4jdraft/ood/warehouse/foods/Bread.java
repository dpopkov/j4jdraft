package ru.j4jdraft.ood.warehouse.foods;

import ru.j4jdraft.ood.warehouse.Food;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Bread extends Food {
    public Bread(LocalDate createDate, LocalDate expireDate, BigDecimal price) {
        super("Bread", createDate, expireDate, price, null);
    }
}
