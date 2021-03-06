package ru.j4jdraft.ood.tictac;

import ru.j4jdraft.ood.tictac.model.Config;

import java.util.Map;
import java.util.function.Supplier;

public class ArgsConfig implements Config {
    private static final int DEFAULT_GRID_SIZE = 3;
    private static final String DEFAULT_UI_TYPE = "fx";
    private static final int DEFAULT_STARTING_ID = 1;
    private static final int DEFAULT_WINNING_LINE_LENGTH = 3;
    private static final ArgsConfig INSTANCE = new ArgsConfig();

    public static ArgsConfig instance() {
        return INSTANCE;
    }

    /** Command line arguments. */
    private String[] args;
    /** Index of the current argument. */
    private int idx;
    private boolean initialized = false;
    private String uiType = DEFAULT_UI_TYPE;
    private int gridSize = DEFAULT_GRID_SIZE;
    private int startingId = DEFAULT_STARTING_ID;
    private int winLineLength = DEFAULT_WINNING_LINE_LENGTH;

    /** Actions used to receive values of the arguments. */
    private final Map<String, Supplier<Object>> actions = Map.of(
            "-u", () -> uiType = nextArg(),
            "-s", () -> gridSize = Integer.parseInt(nextArg()),
            "-i", () -> startingId = Integer.parseInt(nextArg()),
            "-w", () -> winLineLength = Integer.parseInt(nextArg())
    );

    private String nextArg() {
        return args[++idx];
    }

    private ArgsConfig() {
    }

    public void init(String[] args) {
        this.args = args;
        parseArgs();
        initialized = true;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    @Override
    public String uiType() {
        checkInitialized();
        return uiType;
    }

    @Override
    public int gridSize() {
        checkInitialized();
        return gridSize;
    }

    @Override
    public int getStartingId() {
        checkInitialized();
        return startingId;
    }

    @Override
    public int getWinLineLength() {
        checkInitialized();
        return winLineLength;
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
