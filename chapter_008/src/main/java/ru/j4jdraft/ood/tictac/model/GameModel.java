package ru.j4jdraft.ood.tictac.model;

import java.util.ArrayList;
import java.util.List;

/*
Позволяет вводить ходы до завершения игры.
Является Observable
 */
public class GameModel {
    private final GameGrid grid;
    private final Grid gridView;
    private boolean finished;
    private List<GameObserver> observers = new ArrayList<>();

    public GameModel(GameGrid grid) {
        this.grid = grid;
        gridView = new UnmodifiableGrid(grid);
    }

    public Grid getGrid() {
        return gridView;
    }

    public void start(PlayerId startingPlayer) {
        notifyObservers(GameEvent.GAME_STARTED, startingPlayer);
    }

    // todo: может быть стоит заменить int id на класс PlayerId, который будет хранить id и mark.
    public void move(PlayerId playerId, Move move) {
        // поставить символ соответствующего игрока на поле
        int row = move.getRow();
        int col = move.getCol();
        if (grid.isFreeAt(row, col)) {
            grid.setMark(row, col, playerId.getMark());
        } else {
            throw new IllegalMoveException("Cell at position " + move + " is not free");
        }
        // check state of the game
        GameEvent event = gameFinished() ? GameEvent.GAME_FINISHED : GameEvent.PLAYER_MOVED;
        notifyObservers(event, playerId);
    }

    private boolean gameFinished() {
        // todo: implement
        return false;
    }

    public int getWinnerId() {
        if (finished) {
            // если игра закончена, то вернуть айди победителя
            return -1;
        } else {
            throw new IllegalStateException("Can not get winner because the game is not finished yet");
        }
    }

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(GameEvent event, PlayerId activePlayer) {
        for (GameObserver observer : observers) {
            observer.update(event, activePlayer);
        }
    }
}
