package ru.j4jdraft.mt.bomberman.gui;

import javafx.event.EventHandler;
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
        EventHandler<MouseEvent> lblHandler = e -> {
            GridLabel gl = (GridLabel) e.getSource();
            System.out.println("col=" + gl.getCol() + ", row=" + gl.getRow());
        };
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                GridLabel label = new GridLabel(row, col);
                label.setOnMouseClicked(lblHandler);
                labels[row][col] = label;
                this.add(label, col, row);
            }
        }
        return labels;
    }

    public GridLabel getLabel(int row, int col) {
        return labels[row][col];
    }

    public static class GridLabel extends ImageView {
        private final int row;
        private final int col;
        private LabelState state;

        public GridLabel(int row, int col) {
            super(LabelState.FREE.getImage());
            state = LabelState.FREE;
            this.row = row;
            this.col = col;
        }

        public LabelState getState() {
            return state;
        }

        public void changeState(LabelState state) {
            this.state = state;
            setImage(state.getImage());
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
