package ru.j4jdraft.ood.tictac.fx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class GameView {
    private static final int CELL_SIZE = 40;
    private static final String EMPTY_CELL_REPRESENTATION = "   ";

    private final Pane root;
    private final Label messageLabel = new Label();
    private final GridPane buttonsPane = new GridPane();
    private Button[][] buttons;

    public GameView(HumanGameController controller) {
        controller.setView(this);
        root = createNodeHierarchy(controller);
    }

    public Pane getRoot() {
        return root;
    }

    public void displayStateMessage(String message) {
        messageLabel.setText(message);
    }

    public void updateCell(int row, int col, String s) {
        buttons[row][col].setText(s);
    }

    private Pane createNodeHierarchy(HumanGameController controller) {
        buttonsPane.setPadding(new Insets(20));
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
        int gridSize = controller.getGridSize();
        buttons = new Button[gridSize][gridSize];
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                Button button = new Button(EMPTY_CELL_REPRESENTATION);
                GridPane.setRowIndex(button, row);
                GridPane.setColumnIndex(button, col);
                button.setPrefSize(CELL_SIZE, CELL_SIZE);
                button.setOnAction(buttonEventHandler);
                buttons[row][col] = button;
                buttonsPane.add(button, col, row);
            }
        }
        Pane vBox = new VBox(5);
        vBox.getChildren().addAll(messageLabel, buttonsPane);
        return vBox;
    }

    private void displayWarningMessage(String message) {
        Alert.AlertType alertAlertType = Alert.AlertType.WARNING;
        Alert alert = new Alert(alertAlertType);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
