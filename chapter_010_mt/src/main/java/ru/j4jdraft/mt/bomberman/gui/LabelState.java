package ru.j4jdraft.mt.bomberman.gui;

import javafx.scene.image.Image;

import java.net.URL;

public enum LabelState {
    FREE("images/BlockWhite32.png"),
    BLOCK("images/BlockGray32.png"),
    HERO("images/BlockGreen32.png"),
    MONSTER("images/BlockRed32.png");

    private final Image image;

    LabelState(String imageFile) {
        image = loadImageFile(imageFile);
    }

    public Image getImage() {
        return image;
    }

    private static Image loadImageFile(String imageFile) {
        URL url = GridView.GridLabel.class.getClassLoader().getResource(imageFile);
        if (url == null) {
            throw new RuntimeException("Can not load image file: " + imageFile);
        }
        return new Image(url.toString());
    }
}
