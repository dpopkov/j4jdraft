package ru.j4jdraft.ood.tictac.fx;

import ru.j4jdraft.ood.tictac.model.*;

public class HumanGameController implements GameObserver, GridObserver {
    private final GameModel model;
    private final PlayerId playerId;
    private GameView view;
    private boolean yourTurn;

    public HumanGameController(GameModel model, PlayerId playerId) {
        this.model = model;
        this.playerId = playerId;
        model.getGrid().addObserver(this);
    }

    public void setView(GameView view) {
        this.view = view;
    }

    public int getGridSize() {
        return model.getGrid().size();
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
                view.displayStateMessage("Your turn");
                yourTurn = true;
            } else {
                view.displayStateMessage("");
                yourTurn = false;
            }
        } else if (event == GameEvent.GAME_FINISHED) {
            String result = playerId.getId() == model.getWinnerId() ? "You won!" : "You lost";
            view.displayStateMessage("Game finished. " + result);
        }
    }

    @Override
    public void update(Position position, Mark mark) {
        view.updateCell(position.getRow(), position.getCol(), mark.toString());
    }
}
