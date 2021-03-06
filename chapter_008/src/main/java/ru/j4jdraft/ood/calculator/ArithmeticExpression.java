package ru.j4jdraft.ood.calculator;

public class ArithmeticExpression implements Expression {
    private Expression first;
    private final Expression second;
    private final ArithmeticOperation operation;
    private final ArithmeticCalculator calculator;

    public ArithmeticExpression(Expression first, Expression second,
                                ArithmeticOperation operation, ArithmeticCalculator calculator) {
        this.first = first;
        this.second = second;
        this.operation = operation;
        this.calculator = calculator;
    }

    @Override
    public double evaluate() {
        return calculator.calculate(first.evaluate(), operation, second.evaluate());
    }
}
