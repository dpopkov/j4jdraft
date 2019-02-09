package ru.j4jdraft.io.demos5;

import java.io.Serializable;

public class Goods implements Serializable {
    final String name;
    final int amount;
    final double price;

    public Goods(String name, int amount, double price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
