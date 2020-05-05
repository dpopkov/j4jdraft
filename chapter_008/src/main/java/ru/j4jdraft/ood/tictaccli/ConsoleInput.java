package ru.j4jdraft.ood.tictaccli;

import java.io.InputStream;
import java.util.Scanner;

public class ConsoleInput implements Input {
    private final Output output;
    private final Scanner scanner;

    public ConsoleInput(Output output, InputStream inputStream) {
        this.output = output;
        scanner = new Scanner(inputStream);
    }

    @Override
    public Position requestPosition(String prompt) {
        while (true) {
            try {
                output.print(prompt);
                String answer = scanner.nextLine();
                String[] tokens = answer.split("\\s+");
                int row = Integer.parseInt(tokens[0]);
                int col = Integer.parseInt(tokens[1]);
                return new Position(row, col);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                output.print("Incorrect position. Try again");
            }
        }
    }
}
