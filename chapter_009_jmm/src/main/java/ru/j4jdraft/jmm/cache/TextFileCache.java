package ru.j4jdraft.jmm.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;

public class TextFileCache {
    private final Cache<String, String> cache;

    public TextFileCache(Path dirPath) {
        Function<String, String> textProvider = filename -> {
            try {
                return Files.readString(dirPath.resolve(filename));
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        };
        cache = new Cache<>(textProvider);
    }

    public String getContents(String filename) {
        return cache.get(filename);
    }
}
