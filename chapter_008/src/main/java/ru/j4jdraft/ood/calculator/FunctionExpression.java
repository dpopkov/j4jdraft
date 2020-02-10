package ru.j4jdraft.ood.calculator;

public class FunctionExpression implements Expression {
    private final Expression argument;
    private final FunctionName functionName;
    private final FunctionCalculator calculator;

    public FunctionExpression(Expression argument, FunctionName functionName, FunctionCalculator calculator) {
        this.argument = argument;
        this.functionName = functionName;
        this.calculator = calculator;
    }

    @Override
    public double evaluate() {
        return calculator.calculate(functionName, argument.evaluate());
    }
}
