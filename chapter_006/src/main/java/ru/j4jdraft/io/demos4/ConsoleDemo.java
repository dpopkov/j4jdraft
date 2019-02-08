package ru.j4jdraft.io.demos4;

import java.io.Console;

/**
 * Demonstrates {@code Console} usage.
 * This example will not work in IDE (Idea),
 * but it should work in command line.
 */
public class ConsoleDemo {
    public static void main(String[] args) {
        Console console = System.console();
        if (console == null) {
            System.out.println("There is no console available");
            return;
        }
        String str = console.readLine("Enter a string: ");
        console.printf("Here is your string: %s%n", str);
    }
}
