package ru.j4jdraft.ood.tictac.fx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import ru.j4jdraft.ood.tictac.model.Grid;
import ru.j4jdraft.ood.tictac.model.Position;

public class GameView {
    private static final int CELL_SIZE = 40;

    private final Pane root;
    private final HumanGameController controller;
    private Label messageLabel = new Label();

    public GameView(HumanGameController controller) {
        this.controller = controller;
        controller.setView(this);
        root = createNodeHierarchy();
    }

    public Pane getRoot() {
        return root;
    }

    public void showStateMessage(String message) {
        messageLabel.setText(message);
    }

    private Pane createNodeHierarchy() {
        Pane vBox = new VBox(5);
        GridPane pane = new GridPane();
        vBox.getChildren().addAll(messageLabel, pane);
        pane.setPadding(new Insets(20));
        Grid grid = controller.getGrid();
        int gridSize = grid.size();
        EventHandler<ActionEvent> buttonEventHandler = event -> {
            if (!controller.isYourTurn()) {
                displayWarningMessage("Wait please! It is not your turn!");
                return;
            }
            Node source = (Node) event.getSource();
            boolean moveDone = controller.move(GridPane.getRowIndex(source), GridPane.getColumnIndex(source));
            if (!moveDone) {
                displayWarningMessage("This cell is busy!");
            }
        };
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                Button button = new Button(grid.getMark(new Position(row, col)).toString()); // todo: decide how to represent marks
                GridPane.setRowIndex(button, row);
                GridPane.setColumnIndex(button, col);
                button.setPrefSize(CELL_SIZE, CELL_SIZE);
                button.setOnAction(buttonEventHandler);
                pane.add(button, col, row);
            }
        }
        return vBox;
    }

    private void displayWarningMessage(String message) {
        AlertType alertAlertType = AlertType.WARNING;
        Alert alert = new Alert(alertAlertType);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
