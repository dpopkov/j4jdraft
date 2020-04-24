package ru.j4jdraft.ood.tictac.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.j4jdraft.ood.tictac.computer.ComputerGameController;
import ru.j4jdraft.ood.tictac.computer.RandomPlayer;
import ru.j4jdraft.ood.tictac.model.*;

public class MainWindow extends Application {
    private static Config config;

    private Pane mainPane;
    private GameModel game;

    @Override
    public void init() {
        GameGrid grid = new ArrayGrid(config.gridSize());
        game = new GameModel(grid, config.getWinLineLength());
        PlayerId human = new PlayerId(1, Mark.X);
        PlayerId computer = new PlayerId(2, Mark.O);
        game.addPlayer(human);
        game.addPlayer(computer);
        HumanGameController controllerForHuman = new HumanGameController(game, human);
        ComputerGameController controllerForComputer = new ComputerGameController(game, new RandomPlayer(computer));
        game.addObserver(controllerForHuman);
        game.addObserver(controllerForComputer);
        GameView view = new GameView(controllerForHuman);
        mainPane = view.getRoot();
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(mainPane));
        stage.setTitle("MainWindow - Test");
        stage.show();
        game.start(config.getStartingId());
    }

    public static void setConfig(Config appConfig) {
        if (appConfig == null) {
            throw new IllegalArgumentException("Config is null");
        }
        if (appConfig.isInitialized()) {
            config = appConfig;
        } else {
            throw new IllegalStateException("Config is not initialized yet");
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
