package ru.j4jdraft.ood.calculator;

import ru.job4j.calculate.Calculate;

import java.util.Map;
import java.util.function.BiConsumer;

public class ArithmeticCalculatorAdapter implements ArithmeticCalculator {
    private final Calculate calculator;
    /** Map of arithmetic operations (in string format) to concrete methods. */
    private Map<ArithmeticOperation, BiConsumer<Double, Double>> operationsMap;

    public ArithmeticCalculatorAdapter(Calculate calculator) {
        this.calculator = calculator;
        initOperations();
    }

    @Override
    public double calculate(double op1, ArithmeticOperation operation, double op2) {
        operationsMap.get(operation).accept(op1, op2);
        return calculator.getResult();
    }

    private void initOperations() {
        operationsMap = Map.of(
                ArithmeticOperation.ADD, calculator::add,
                ArithmeticOperation.SUBTRACT, calculator::subtract,
                ArithmeticOperation.MULTIPLY, calculator::multiply,
                ArithmeticOperation.DIVIDE, calculator::div
        );
    }
}
