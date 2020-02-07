package ru.j4jdraft.ood.calculator;

/**
 * Evaluates arithmetic expressions in string format.
 */
public class SimpleExpressionEvaluator implements ExpressionEvaluator {
    /** Parser of expressions. */
    private final ExpressionParser parser;

    /**
     * Constructs evaluator using the specified parser.
     * @param parser parser that should produce valid expression objects
     */
    public SimpleExpressionEvaluator(ExpressionParser parser) {
        this.parser = parser;
    }

    /**
     * Evaluates an expression that should contain first operand, operation, and second operand,
     * or a single number, or function.
     * @param expr expression
     * @return result of evaluation
     */
    @Override
    public double evaluate(String expr) {
        Expression result = parser.parse(expr);
        if (!result.isFull()) {
            throw new IllegalArgumentException("The expression is not full: " + expr);
        }
        return result.evaluate();
    }

    /**
     * Evaluates an expression that should contain operation and the second operand.
     * @param firstOperand result of previous calculation
     * @param expr expression
     * @return result of evaluation
     */
    @Override
    public double evaluate(double firstOperand, String expr) {
        return evaluate(firstOperand + " " + expr);
    }
}
