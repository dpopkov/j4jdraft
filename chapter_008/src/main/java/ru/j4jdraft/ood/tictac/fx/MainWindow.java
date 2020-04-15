package ru.j4jdraft.ood.tictac.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.j4jdraft.ood.tictac.Config;
import ru.j4jdraft.ood.tictac.model.ArrayGrid;
import ru.j4jdraft.ood.tictac.model.GameGrid;
import ru.j4jdraft.ood.tictac.model.GameModel;

public class MainWindow extends Application {
    private Pane gridPane;

    @Override
    public void init() {
        GameGrid grid = new ArrayGrid(Config.instance().gridSize());
        GameModel model = new GameModel(grid);
        GameController controller = new GameController(model);
        GameView view = new GameView(controller);
        gridPane = view.getRoot();
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(gridPane));
        stage.setTitle("MainWindow - Test");
        stage.show();
    }

    public static void main(String[] args) {
        if (!Config.instance().isInitialized()) {
            Config.instance().init(args);
        }
        Application.launch(args);
    }
}
