package ru.j4jdraft.ood.tictaccli;

import java.io.PrintStream;

public class ConsoleOutput implements Output {
    private final GridFormatter formatter;
    private final PrintStream printStream;

    public ConsoleOutput(GridFormatter formatter, PrintStream printStream) {
        this.formatter = formatter;
        this.printStream = printStream;
    }

    @Override
    public void printGrid(GridView grid) {
        printStream.print(formatter.format(grid));
    }

    @Override
    public void print(String message) {
        printStream.print(message);
    }
}
