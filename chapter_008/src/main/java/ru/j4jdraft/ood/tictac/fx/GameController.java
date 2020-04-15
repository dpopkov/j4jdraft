package ru.j4jdraft.ood.tictac.fx;

import ru.j4jdraft.ood.tictac.model.*;

public class GameController {
    private GameModel model;

    public GameController(GameModel model) {
        this.model = model;
    }

    public Grid getGrid() {
        return model.getGrid();
    }

    public void move(int row, int col) {
        model.move(1, new Move(row, col));
    }
}
