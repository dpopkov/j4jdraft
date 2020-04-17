package ru.j4jdraft.ood.tictac.fx;

import ru.j4jdraft.ood.tictac.model.*;

public class HumanGameController implements GameObserver {
    private final GameModel model;
    private final PlayerId playerId;

    public HumanGameController(GameModel model, PlayerId playerId) {
        this.model = model;
        this.playerId = playerId;
    }

    public Grid getGrid() {
        return model.getGrid();
    }

    public void move(int row, int col) {
        model.move(playerId, new Move(row, col));
    }

    @Override
    public void update(GameEvent event, PlayerId activePlayer) {
        if (event == GameEvent.GAME_STARTED && playerId.equals(activePlayer)) {
            // todo: display a message
        }

        // todo: if game finished then show result in View
    }
}
