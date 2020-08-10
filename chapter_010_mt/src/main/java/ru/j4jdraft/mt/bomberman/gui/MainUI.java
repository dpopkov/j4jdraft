package ru.j4jdraft.mt.bomberman.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.j4jdraft.mt.bomberman.*;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
--module-path c:\path-to-javafx\javafx-sdk-11\lib
--add-modules=javafx.controls,javafx.fxml
 */
public class MainUI extends Application {

    private static final int SIZE = 10;
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
        List<Position> blocks = List.of(new Position(1, 1), new Position(8, 8),
                new Position(3, 3), new Position(6, 6),
                new Position(1, 8), new Position(8, 1));
        Board board = new Board(SIZE, blocks);
        for (Position block : blocks) {
            grid.getLabel(block.getRow(), block.getCol()).changeState(LabelState.BLOCK);
        }
        Gui gui = new Gui() {
            @Override
            public void occupyOnGui(Position cell, LabelState state) {
                Platform.runLater(
                        () -> grid.getLabel(cell.getRow(), cell.getCol()).changeState(state)
                );
            }

            @Override
            public void updateGui(Position from, Position to) {
                Platform.runLater(() -> {
                    GridView.GridLabel source = grid.getLabel(from.getRow(), from.getCol());
                    GridView.GridLabel target = grid.getLabel(to.getRow(), to.getCol());
                    source.transitionTo(target);
                });
            }
        };
        Position pos1 = new Position(board.size() / 2, board.size() / 2);
        Position pos2 = pos1.inDirection(Direction.DOWN).inDirection(Direction.DOWN);
        HeroTask heroTask1 = new HeroTask(board, pos1, heroDelay, gui);
        HeroTask heroTask2 = new HeroTask(board, pos2, heroDelay, gui);
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        ExecutorService exec = Executors.newFixedThreadPool(2);
        exec.execute(heroTask1);
        exec.execute(heroTask2);
        primaryStage.setOnCloseRequest(e -> exec.shutdownNow());
        primaryStage.show();
    }
}
