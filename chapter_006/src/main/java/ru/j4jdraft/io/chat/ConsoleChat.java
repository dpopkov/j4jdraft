package ru.j4jdraft.io.chat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ConsoleChat {
    private final Path inputFile;
    private final Path logFile;
    private Random random;

    public ConsoleChat(Path inputFile, Path logFile) {
        this.inputFile = inputFile;
        this.logFile = logFile;
        random = new Random();
    }

    public void start() throws IOException {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        List<String> answers = Files.readAllLines(inputFile);
        Chat chat = new Chat(scanner::nextLine,
                () -> answers.get(random.nextInt(answers.size())),
                System.out::println,
                new SimpleLogger(logFile));
        chat.start();
    }

    /**
     * Sets random with fixed seed for testing purposes.
     * @param random random with fixed seed
     */
    void setRandom(Random random) {
        this.random = random;
    }
}
