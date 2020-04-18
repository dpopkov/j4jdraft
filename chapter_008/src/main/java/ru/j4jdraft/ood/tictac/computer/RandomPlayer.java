package ru.j4jdraft.ood.tictac.computer;

import ru.j4jdraft.ood.tictac.model.Grid;
import ru.j4jdraft.ood.tictac.model.Position;
import ru.j4jdraft.ood.tictac.model.Player;
import ru.j4jdraft.ood.tictac.model.PlayerId;

public class RandomPlayer implements Player {
    private final PlayerId playerId;

    public RandomPlayer(PlayerId playerId) {
        this.playerId = playerId;
    }

    @Override
    public PlayerId getPlayerId() {
        return playerId;
    }

    @Override
    public Position makeMove(Grid grid) {
        // todo: produce random move
        return null;
    }
}
