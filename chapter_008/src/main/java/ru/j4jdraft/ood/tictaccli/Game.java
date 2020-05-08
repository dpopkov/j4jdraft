package ru.j4jdraft.ood.tictaccli;

import java.io.InputStream;
import java.io.PrintStream;

public class Game {
    private final Output output;
    private final Input input;
    private final GameCycle cycle;

    public Game(Config config, PrintStream out, InputStream in) {
        output = new ConsoleOutput(new PseudoTextGridFormatter(), out);
        input = new ConsoleInput(output, in);
        cycle = initCycle(config);
    }

    private GameCycle initCycle(Config config) {
        Player human = new HumanPlayer(Mark.X, input);
        Player computer = new RandomComputerPlayer(Mark.O, config.getAnswerDelay());
        int startingId = config.getStartingId();
        Player first = startingId == 1 ? human : computer;
        Player second = startingId == 1 ? computer : human;
        GameGrid grid = new ArrayGrid(config.gridSize());
        return new GameCycle(grid, output, first, second, config.getWinLineLength());
    }

    public void run() {
        cycle.start();
        Mark winner = cycle.getWinner();
        if (winner != null) {
            output.println(winner + " is the winner");
        } else {
            output.println("The game ended in a draw");
        }
    }
}
