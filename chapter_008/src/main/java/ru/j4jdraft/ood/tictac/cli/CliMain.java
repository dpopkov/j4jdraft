package ru.j4jdraft.ood.tictac.cli;

import ru.j4jdraft.ood.tictac.Config;
import ru.j4jdraft.ood.tictac.computer.ComputerGameController;
import ru.j4jdraft.ood.tictac.computer.RandomPlayer;
import ru.j4jdraft.ood.tictac.model.*;

public class CliMain {
    private GameModel game;

    private CliMain() {
        init();
    }

    private void start() {
        game.start(Config.instance().getStartingId());
    }

    private void init() {
        // create players
        PlayerId human = new PlayerId(1, Mark.X);
        PlayerId computer = new PlayerId(2, Mark.O);
        // create game
        GameGrid grid = new ArrayGrid(3);
        int winLineLength = 3;
        game = new GameModel(grid, winLineLength);
        game.addPlayer(human);
        game.addPlayer(computer);
        // create controllers
        HumanCliController controllerForHuman = new HumanCliController(game);
        ComputerGameController controllerForComputer = new ComputerGameController(game, new RandomPlayer(computer));
        game.addObserver(controllerForHuman);
        game.addObserver(controllerForComputer);
    }

    public static void main(String[] args) {
        if (!Config.instance().isInitialized()) {
            Config.instance().init(args);
        }
        CliMain cli = new CliMain();
        cli.start();
    }
}
