package ru.j4jdraft.ood.warehouse;

import ru.j4jdraft.ood.warehouse.quality.Discountable;
import ru.j4jdraft.ood.warehouse.quality.Expirable;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Food implements Expirable, Discountable {
    private final String name;
    private final LocalDate createDate;
    private final LocalDate expireDate;
    private final BigDecimal price;
    private BigDecimal discount;

    public Food(String name, LocalDate createDate, LocalDate expireDate, BigDecimal price, BigDecimal discount) {
        this.name = name;
        this.createDate = createDate;
        this.expireDate = expireDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    @Override
    public LocalDate created() {
        return createDate;
    }

    @Override
    public LocalDate expires() {
        return expireDate;
    }

    @Override
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Food{name='" + name + '\'' + ", createDate=" + createDate + ", expireDate=" + expireDate
                + ", price=" + price + ", discount=" + discount + '}';
    }
}
