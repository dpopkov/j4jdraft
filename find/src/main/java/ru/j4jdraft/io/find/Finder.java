package ru.j4jdraft.io.find;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Searches files by criteria.
 */
public class Finder {
    private final Path startDir;

    public Finder(Path startDir) {
        this.startDir = startDir;
    }

    public List<Path> find(String name, SearchBy searchBy) throws IOException {
        List<Path> result = null;
        if (searchBy == SearchBy.FULL) {
            result = Files.list(startDir)
                    .filter(p -> p.getFileName().toString().equals(name))
                    .collect(Collectors.toList());
        } else if (searchBy == SearchBy.MASK) {
            PathMatcher pathMatcher = startDir.getFileSystem().getPathMatcher("glob:" + name);
            result = Files.list(startDir)
                    .filter(p -> pathMatcher.matches(p.getFileName()))
                    .collect(Collectors.toList());
        } else if (searchBy == SearchBy.REGEX) {
            // TODO: implement
        }
        return result;
    }
}
