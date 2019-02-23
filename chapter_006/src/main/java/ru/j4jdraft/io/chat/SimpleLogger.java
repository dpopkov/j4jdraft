package ru.j4jdraft.io.chat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.function.Consumer;

public class SimpleLogger implements Consumer<String> {
    private static final String NL = System.lineSeparator();

    private final Path path;

    public SimpleLogger(Path path) {
        this.path = path;
    }

    @Override
    public void accept(String s) {
        try {
            Files.writeString(path, s + NL,
                    StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Error writing log", e);
        }
    }
}
