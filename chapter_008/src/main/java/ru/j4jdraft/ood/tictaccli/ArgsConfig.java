package ru.j4jdraft.ood.tictaccli;

import java.util.Map;
import java.util.function.Supplier;

public class ArgsConfig implements Config {
    private static final int DEFAULT_GRID_SIZE = 3;
    private static final int DEFAULT_STARTING_ID = 1;
    private static final int DEFAULT_WINNING_LINE_LENGTH = 3;
    private static final long DEFAULT_ANSWER_DELAY = 700L;
    private static final ArgsConfig INSTANCE = new ArgsConfig();

    public static ArgsConfig instance() {
        return INSTANCE;
    }

    /** Command line arguments. */
    private String[] args;
    /** Index of the current argument. */
    private int idx;
    private boolean initialized = false;
    private int gridSize = DEFAULT_GRID_SIZE;
    private int startingId = DEFAULT_STARTING_ID;
    private int winLineLength = DEFAULT_WINNING_LINE_LENGTH;
    private long answerDelay = DEFAULT_ANSWER_DELAY;

    /** Actions used to receive values of the arguments. */
    private final Map<String, Supplier<Object>> actions = Map.of(
            "-s", () -> gridSize = Integer.parseInt(nextArg()),
            "-i", () -> startingId = Integer.parseInt(nextArg()),
            "-w", () -> winLineLength = Integer.parseInt(nextArg()),
            "-d", () -> answerDelay = Integer.parseInt(nextArg())
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

    @Override
    public long getAnswerDelay() {
        checkInitialized();
        return answerDelay;
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
