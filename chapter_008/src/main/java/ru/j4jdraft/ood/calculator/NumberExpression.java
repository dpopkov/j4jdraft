package ru.j4jdraft.ood.calculator;

import java.util.Objects;

public class NumberExpression implements Expression {
    private final double value;

    public NumberExpression(double value) {
        this.value = value;
    }

    @Override
    public double evaluate() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NumberExpression that = (NumberExpression) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "NumberExpression{value=" + value + '}';
    }
}
