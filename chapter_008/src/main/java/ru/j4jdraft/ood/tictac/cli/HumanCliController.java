package ru.j4jdraft.ood.tictac.cli;

import ru.j4jdraft.ood.tictac.model.*;

public class HumanCliController implements GameObserver {
    private final GameModel model;
    private final PlayerId playerId;
    private GridFormatter formatter; // todo: implement class PseudoTextGridFormatter

    public HumanCliController(GameModel model, PlayerId playerId) {
        this.model = model;
        this.playerId = playerId;
    }

    public String getGrid() {
        return "";
    }

    public boolean move(String coordinates) {
        return false;
    }

    @Override
    public void update(GameEvent event, PlayerId activePlayer) {
        boolean forMe = activePlayer.equals(playerId);
        if (forMe && event == GameEvent.NEXT_MOVE) {
            Position position; // todo: get position from User
            position = null;
            model.move(playerId, position);
        }
    }
}
