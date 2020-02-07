package ru.j4jdraft.ood.calculator;

import java.util.Map;
import java.util.function.UnaryOperator;

public class SimpleFunctionCalculator implements FunctionCalculator {
    private static final Map<FunctionName, UnaryOperator<Double>> functions = Map.of(
            FunctionName.SIN, Math::sin,
            FunctionName.COS, Math::cos,
            FunctionName.TAN, Math::tan
    );

    @Override
    public double calculate(FunctionName name, double argument) {
        return functions.get(name).apply(Math.toRadians(argument));
    }
}
