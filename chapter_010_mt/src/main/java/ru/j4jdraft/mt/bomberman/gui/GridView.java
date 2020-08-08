package ru.j4jdraft.mt.bomberman.gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class GridView extends GridPane {
    private final GridLabel[][] labels;

    public GridView(int size) {
        labels = initLabels(size);
        setGridLinesVisible(true);
    }

    private GridLabel[][] initLabels(int size) {
        GridLabel[][] labels = new GridLabel[size][size];
        Insets padding = new Insets(5);
        EventHandler<MouseEvent> lblHandler = e -> {
            GridLabel gl = (GridLabel) e.getSource();
            System.out.println("col=" + gl.getCol() + ", row=" + gl.getRow());
        };
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                GridLabel label = new GridLabel(i, j);
                label.setPadding(padding);
                label.setOnMouseClicked(lblHandler);
                labels[i][j] = label;
                this.add(label, i, j);
            }
        }
        return labels;
    }

    public GridLabel getLabel(int row, int col) {
        return labels[row][col];
    }

    public static class GridLabel extends Label {
        private final int row;
        private final int col;
        private LabelState state;

        public GridLabel(int row, int col) {
            super("", new ImageView(LabelState.FREE.getImage()));
            state = LabelState.FREE;
            this.row = row;
            this.col = col;
        }

        public LabelState getState() {
            return state;
        }

        public void changeState(LabelState state) {
            this.state = state;
            ImageView vi = (ImageView) this.getChildren().get(0);
            vi.setImage(state.getImage());
        }

        public void transitionTo(GridLabel target) {
            LabelState targetState = target.getState();
            target.changeState(this.getState());
            this.changeState(targetState);
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }
}
