package ru.j4jdraft.ood.tictac.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.j4jdraft.ood.tictac.Config;
import ru.j4jdraft.ood.tictac.computer.ComputerGameController;
import ru.j4jdraft.ood.tictac.computer.RandomPlayer;
import ru.j4jdraft.ood.tictac.model.*;

public class MainWindow extends Application {
    private Pane gridPane;
    private GameModel game;
    private PlayerId startingPlayer;

    @Override
    public void init() {
        GameGrid grid = new ArrayGrid(Config.instance().gridSize());
        game = new GameModel(grid);
        PlayerId human = new PlayerId(1, Mark.X);
        PlayerId computer = new PlayerId(2, Mark.O);
        startingPlayer = Config.instance().getStartingId() == 1 ? human : computer;
        HumanGameController controllerForHuman = new HumanGameController(game, human);
        ComputerGameController controllerForComputer = new ComputerGameController(game, new RandomPlayer(computer));
        game.addObserver(controllerForHuman);
        game.addObserver(controllerForComputer);
        GameView view = new GameView(controllerForHuman);
        gridPane = view.getRoot();
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(gridPane));
        stage.setTitle("MainWindow - Test");
        stage.show();
        game.start(startingPlayer);
    }

    public static void main(String[] args) {
        if (!Config.instance().isInitialized()) {
            Config.instance().init(args);
        }
        Application.launch(args);
    }
}
