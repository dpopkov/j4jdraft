package ru.j4jdraft.mt.bomberman.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
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

    private Position hero;

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
        List<Position> monstersPositions = List.of(new Position(0, 0),
                new Position(9, 9), new Position(0, 9),
                new Position(9, 2));
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
        hero = new Position(board.size() / 2, board.size() / 2);
        board.occupy(hero);
        gui.occupyOnGui(hero, LabelState.HERO);
        ExecutorService exec = Executors.newFixedThreadPool(monstersPositions.size());
        startPersonages(board, exec, monstersPositions, gui);
        Scene scene = new Scene(grid);
        scene.setOnKeyPressed(e -> {
            Position oldPos = hero;
            Position newPos = getPositionFrom(e.getCode());
            try {
                hero = board.move(hero, newPos);
                gui.updateGui(oldPos, hero);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(e -> exec.shutdownNow());
        primaryStage.show();
    }

    private Position getPositionFrom(KeyCode keyCode) {
        int col = hero.getCol();
        int row = hero.getRow();
        switch (keyCode) {
            case UP:
                row--;
                break;
            case DOWN:
                row++;
                break;
            case LEFT:
                col--;
                break;
            case RIGHT:
                col++;
                break;
            default:
                break;
        }
        return new Position(row, col);
    }

    private void startPersonages(Board board, ExecutorService exec,
                                 List<Position> monstersPositions, Gui gui) {
        for (Position p : monstersPositions) {
            MonsterTask monsterTask = new MonsterTask(board, p, heroDelay, gui);
            exec.execute(monsterTask);
        }
    }
}
