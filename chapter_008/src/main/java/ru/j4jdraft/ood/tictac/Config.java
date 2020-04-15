package ru.j4jdraft.ood.tictac;

import java.util.Map;
import java.util.function.Supplier;

public class Config {
    private static final int DEFAULT_GRID_SIZE = 3;
    private static final String DEFAULT_UI_TYPE = "fx";

    private static final Config instance = new Config();

    public static Config instance() {
        return instance;
    }

    /** Command line arguments. */
    private String[] args;
    /** Index of the current argument. */
    private int idx;
    private boolean initialized = false;
    private String uiType = DEFAULT_UI_TYPE;
    private int gridSize = DEFAULT_GRID_SIZE;
    /** Actions used to receive values of the arguments. */
    private final Map<String, Supplier<Object>> actions = Map.of(
            "-u", () -> uiType = nextArg(),
            "-s", () -> gridSize = Integer.parseInt(nextArg())
    );

    private String nextArg() {
        return args[++idx];
    }

    private Config() {
    }

    public void init(String[] args) {
        this.args = args;
        parseArgs();
        initialized = true;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public String uiType() {
        checkInitialized();
        return uiType;
    }

    public int gridSize() {
        checkInitialized();
        return gridSize;
    }

    /** Produces parsed arguments. */
    private void parseArgs() {
        for (idx = 0; idx < args.length; idx++) {
            String arg = args[idx];
            actions.getOrDefault(arg, () -> {
                throw new IllegalArgumentException(String.format("Invalid argument: '%s'", arg));
            }).get();
        }
    }

    private void checkInitialized() {
        if (!initialized) {
            throw new IllegalStateException("This config instance is not initialized yet");
        }
    }
}
