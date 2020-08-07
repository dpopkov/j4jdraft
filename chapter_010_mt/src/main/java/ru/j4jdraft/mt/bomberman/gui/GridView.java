package ru.j4jdraft.mt.bomberman.gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;

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
        private static final String WHITE_IMAGE_FILE = "images/BlockWhite32.png";
        private static final String RED_IMAGE_FILE = "images/BlockRed32.png";

        private static Image imageWhite;
        private static Image imageRed;

        private final int row;
        private final int col;
        private boolean green = true;

        static {
            initImageViews();
        }

        public GridLabel(int row, int col) {
            super("", new ImageView(imageWhite));
            this.row = row;
            this.col = col;
        }

        private static void initImageViews() {
            imageWhite = loadImageFile(WHITE_IMAGE_FILE);
            imageRed = loadImageFile(RED_IMAGE_FILE);
        }

        private static Image loadImageFile(String imageFile) {
            URL url = GridLabel.class.getClassLoader().getResource(imageFile);
            if (url == null) {
                throw new RuntimeException("Can not load image file: " + imageFile);
            }
            return new Image(url.toString());
        }

        public void flip() {
            ImageView vi = (ImageView) this.getChildren().get(0);
            if (green) {
                vi.setImage(imageRed);
                green = false;
            } else {
                vi.setImage(imageWhite);
                green = true;
            }
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }
}
