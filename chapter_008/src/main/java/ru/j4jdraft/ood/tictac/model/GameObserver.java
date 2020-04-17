package ru.j4jdraft.ood.tictac.model;

public interface GameObserver {
    /**
     * Notifies an observer about changing state of the game.
     * @param event type of the event
     * @param activePlayer active player
     */
    void update(GameEvent event, PlayerId activePlayer);
}
