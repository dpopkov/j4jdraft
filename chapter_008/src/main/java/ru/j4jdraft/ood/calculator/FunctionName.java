package ru.j4jdraft.ood.calculator;

public enum FunctionName {
    SIN,
    COS,
    TAN;

    public static FunctionName from(String s) {
        return FunctionName.valueOf(s.toUpperCase());
    }
}
