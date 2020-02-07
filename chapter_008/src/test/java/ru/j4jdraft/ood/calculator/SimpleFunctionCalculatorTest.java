package ru.j4jdraft.ood.calculator;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleFunctionCalculatorTest {

    public static final double DELTA = 1e-15;

    @Test
    public void canCalculateSinus() {
        FunctionCalculator calc = new SimpleFunctionCalculator();
        double result = calc.calculate(FunctionName.SIN, 30);
        assertThat(result, Matchers.closeTo(0.5, DELTA));
    }

    @Test
    public void canCalculateCosine() {
        FunctionCalculator calc = new SimpleFunctionCalculator();
        double result = calc.calculate(FunctionName.COS, 60);
        assertThat(result, Matchers.closeTo(0.5, DELTA));
    }

    @Test
    public void canCalculateTangent() {
        FunctionCalculator calc = new SimpleFunctionCalculator();
        double result = calc.calculate(FunctionName.TAN, 45);
        assertThat(result, Matchers.closeTo(1.0, DELTA));
    }
}
