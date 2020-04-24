package ru.j4jdraft.ood.tictac.cli;

import ru.j4jdraft.ood.tictac.computer.ComputerGameController;
import ru.j4jdraft.ood.tictac.computer.RandomPlayer;
import ru.j4jdraft.ood.tictac.model.*;

public class CliMain {
    private final Config config;
    private GameModel game;

    public CliMain(Config config) {
        if (config == null) {
            throw new IllegalArgumentException("Config is null");
        }
        if (config.isInitialized()) {
            this.config = config;
            init();
        } else {
            throw new IllegalArgumentException("Config is not initialized yet");
        }
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
        HumanCliController controllerForHuman = new HumanCliController(game, human);
        ComputerGameController controllerForComputer = new ComputerGameController(game, new RandomPlayer(computer));
        game.addObserver(controllerForHuman);
        game.addObserver(controllerForComputer);
    }

    public void start() {
        game.start(config.getStartingId());
    }
}
