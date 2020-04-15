package ru.j4jdraft.ood.tictac.fx;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import ru.j4jdraft.ood.tictac.model.Grid;

public class GameView {
    private static final int CELL_SIZE = 40;

    private final Pane root;
    private final GameController controller;

    public GameView(GameController controller) {
        this.controller = controller;
        root = createNodeHierarchy();
    }

    public Pane getRoot() {
        return root;
    }

    private Pane createNodeHierarchy() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        Grid grid = controller.getGrid();
        int gridSize = grid.size();
        Border border = new Border(new BorderStroke(Color.LIGHTSALMON, BorderStrokeStyle.SOLID,
                new CornerRadii(4), null, new Insets(2) ));
        EventHandler<MouseEvent> labelMouseEventHandler = e -> {
            Node n = (Node) e.getSource();
            controller.move(GridPane.getRowIndex(n), GridPane.getColumnIndex(n));
        };
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                Label label = new Label(grid.getMark(row, col).toString()); // todo: decide how to represent marks
                GridPane.setRowIndex(label, row);
                GridPane.setColumnIndex(label, col);
                label.setPrefSize(CELL_SIZE, CELL_SIZE);
                label.setBorder(border);
                label.setOnMouseClicked(labelMouseEventHandler);
                pane.add(label, col, row);
            }
        }
        return pane;
    }
}
