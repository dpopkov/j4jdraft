package ru.j4jdraft.ood.warehouse.foods;

import ru.j4jdraft.ood.warehouse.Food;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cheese extends Food {
    public Cheese(LocalDate createDate, LocalDate expireDate, BigDecimal price) {
        super("Cheese", createDate, expireDate, price, null);
    }
}
