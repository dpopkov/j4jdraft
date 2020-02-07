package ru.j4jdraft.ood.calculator;

import java.util.Map;

public enum ArithmeticOperation {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE;

    static final Map<String, ArithmeticOperation> operations = Map.of(
            "+", ADD,
            "-", SUBTRACT,
            "*", MULTIPLY,
            "/", DIVIDE
    );

    public static ArithmeticOperation from(String s) {
        ArithmeticOperation operation = operations.get(s);
        if (operation == null) {
            throw new IllegalArgumentException("Can not infer any arithmetic operation from this argument: " + s);
        }
        return operation;
    }
}
