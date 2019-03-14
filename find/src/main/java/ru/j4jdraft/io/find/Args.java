package ru.j4jdraft.io.find;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Args {
    private static final String DEFAULT_OUTPUT = "log.txt";

    private Path directory = Paths.get("");
    /** Name of file or search pattern. */
    private String name;
    private SearchBy searchBy = SearchBy.FULL;
    private String output = DEFAULT_OUTPUT;

    public Args(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String value = args[i];
            if ("-d".equals(value)) {
                directory = Paths.get(args[++i]);
            } else if ("-n".equals(value)) {
                name = args[++i];
            } else if ("-o".equals(value)) {
                output = args[++i];
            } else if ("-m".equals(value)) {
                searchBy = SearchBy.MASK;
            } else if ("-f".equals(value)) {
                searchBy = SearchBy.FULL;
            } else if ("-r".equals(value)) {
                searchBy = SearchBy.REGEX;
            } else {
                throw new IllegalArgumentException(String.format("Invalid argument %s", value));
            }
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
