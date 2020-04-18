package ru.j4jdraft.ood.tictac.fx;

import ru.j4jdraft.ood.tictac.model.*;

public class HumanGameController implements GameObserver {
    private final GameModel model;
    private final PlayerId playerId;
    private GameView view;
    private boolean yourTurn;

    public HumanGameController(GameModel model, PlayerId playerId) {
        this.model = model;
        this.playerId = playerId;
    }

    public void setView(GameView view) {
        this.view = view;
    }

    public Grid getGrid() {
        return model.getGrid();
    }

    public boolean move(int row, int col) {
        return model.move(playerId, new Position(row, col));
    }

    public boolean isYourTurn() {
        return yourTurn;
    }

    @Override
    public void update(GameEvent event, PlayerId activePlayer) {
        if (event == GameEvent.NEXT_MOVE) {
            if (playerId.equals(activePlayer)) {
                view.showStateMessage("Your turn");
                yourTurn = true;
            } else {
                view.showStateMessage("");
                yourTurn = false;
            }
        } else if (event == GameEvent.GAME_FINISHED) {
            view.showStateMessage("Game finished");
        }
    }
}
