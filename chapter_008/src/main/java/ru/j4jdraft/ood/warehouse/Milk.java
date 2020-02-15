package ru.j4jdraft.ood.warehouse;

import java.math.BigDecimal;
import java.util.Date;

public class Milk extends Food {
    public Milk(Date createDate, Date expireDate, BigDecimal price) {
        super("Milk", createDate, expireDate, price, null);
    }
}
