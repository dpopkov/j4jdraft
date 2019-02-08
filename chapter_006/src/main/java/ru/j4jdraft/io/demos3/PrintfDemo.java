package ru.j4jdraft.io.demos3;

public class PrintfDemo {
    public static void main(String[] args) {
        System.out.println("Here are some numeric values in different formats.\n");
        System.out.print("Various integer formats: ");
        System.out.printf("%d %(d %+d %05d%n", 3, -3, 3, 3);
        System.out.println();
        System.out.printf("Default floating-point format: %f%n", 1234567.123);
        System.out.printf("Floating-point with dividers: %,f%n", 1234567.123);
        System.out.printf("Negative floating-point default: %,f%n", -1234567.123);
        System.out.printf("Negative floating-point option: %,(f%n", -1234567.123);
        System.out.println();
        System.out.println("Line up positive and negative values:");
        System.out.printf("% ,.2f%n% ,.2f%n", 1234567.123, -1234567.123);
    }
}
