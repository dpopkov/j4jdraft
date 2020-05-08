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
        Player first;
        Player second;
        Config.PlayerType starting = config.getFirstPlayer();
        if (starting == Config.PlayerType.HUMAN) {
            first = human;
            second = computer;
        } else if (starting == Config.PlayerType.COMPUTER) {
            first = computer;
            second = human;
        } else {
            throw new IllegalStateException("Unknown type: " + starting);
        }
        GameGrid grid = new ArrayGrid(config.getGridSize());
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
