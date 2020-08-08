package ru.j4jdraft.mt.bomberman.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.j4jdraft.mt.bomberman.Board;
import ru.j4jdraft.mt.bomberman.Cell;
import ru.j4jdraft.mt.bomberman.Gui;
import ru.j4jdraft.mt.bomberman.HeroThread;

/*
--module-path c:\path-to-javafx\javafx-sdk-11\lib
--add-modules=javafx.controls,javafx.fxml
 */
public class MainUI extends Application {

    private static final int SIZE = 10;
    private static final int NUM_BLOCKS = 8;
    private static long heroDelay = 1000;

    public static void main(String[] args) {
        if (args.length > 0) {
            heroDelay = Long.parseLong(args[0]);
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridView grid = new GridView(SIZE);
        Board board = new Board(SIZE, NUM_BLOCKS);
        Gui gui = new Gui() {
            @Override
            public void occupyOnGui(Cell cell, LabelState state) {
                Platform.runLater(
                        () -> grid.getLabel(cell.getRow(), cell.getCol()).changeState(state)
                );
            }

            @Override
            public void updateGui(Cell from, Cell to) {
                Platform.runLater(() -> {
                    GridView.GridLabel source = grid.getLabel(from.getRow(), from.getCol());
                    GridView.GridLabel target = grid.getLabel(to.getRow(), to.getCol());
                    source.transitionTo(target);
                });
            }
        };
        HeroThread heroThread = new HeroThread(board, heroDelay, gui);
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        heroThread.start();
        primaryStage.setOnCloseRequest(e -> heroThread.interrupt());
        primaryStage.show();
    }
}
