package ru.j4jdraft.io.find;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.*;

public class Args {
    private static final String DEFAULT_OUTPUT = "log.txt";
    /** Command line arguments. */
    private String[] args;
    /** Index of current argument. */
    private int idx;
    private Path directory = Paths.get("");
    /** Name of file or search pattern. */
    private String name;
    private SearchBy searchBy = SearchBy.FULL;
    private String output = DEFAULT_OUTPUT;

    private final Map<String, Supplier<Object>> actions = Map.of(
            "-d", () -> directory = Paths.get(args[++idx]),
            "-n", () -> name = args[++idx],
            "-o", () -> output = args[++idx],
            "-m", () -> searchBy = SearchBy.MASK,
            "-f", () -> searchBy = SearchBy.FULL,
            "-r", () -> searchBy = SearchBy.REGEX
    );

    public Args(String[] args) {
        this.args = args;
        parse();
    }

    private void parse() {
        for (idx = 0; idx < args.length; idx++) {
            String arg = args[idx];
            actions.getOrDefault(arg, () -> {
                throw new IllegalArgumentException(String.format("Invalid argument: '%s'", arg));
            }).get();
        }
    }

    public Path getDirectory() {
        return directory;
    }

    public String getName() {
        return name;
    }

    public SearchBy getSearchBy() {
        return searchBy;
    }

    public String getOutput() {
        return output;
    }
}
