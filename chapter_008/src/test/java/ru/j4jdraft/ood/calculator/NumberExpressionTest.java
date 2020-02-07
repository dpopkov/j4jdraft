package ru.j4jdraft.ood.calculator;

import org.junit.Test;

import static org.junit.Assert.*;

public class NumberExpressionTest {

    @Test
    public void whenEvaluateThenReturnsValue() {
        NumberExpression expr = new NumberExpression(3.14);
        assertEquals(3.14, expr.evaluate(), 1e-15);
    }
}
