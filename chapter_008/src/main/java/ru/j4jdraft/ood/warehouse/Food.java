package ru.j4jdraft.ood.warehouse;

import java.math.BigDecimal;
import java.util.Date;

public class Food implements Expirable {
    private final String name;
    private final Date createDate;
    private final Date expireDate;
    private final BigDecimal price;
    private BigDecimal discount;

    public Food(String name, Date createDate, Date expireDate, BigDecimal price, BigDecimal discount) {
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
    public Date created() {
        return createDate;
    }

    @Override
    public Date expires() {
        return expireDate;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
