package ru.j4jdraft.ood.tictac.computer;

import ru.j4jdraft.ood.tictac.model.*;

public class ComputerGameController implements GameObserver {
    private final GameModel model;
    private final Player player;

    public ComputerGameController(GameModel model, Player player) {
        this.model = model;
        this.player = player;
    }

    @Override
    public void update(GameEvent event, PlayerId activePlayer) {
        boolean isMe = activePlayer.equals(player.getPlayerId());
        if (event == GameEvent.GAME_STARTED && isMe || event == GameEvent.PLAYER_MOVED && !isMe) {
            Move move = player.makeMove(model.getGrid());
            model.move(player.getPlayerId(), move);
        }
    }
}
