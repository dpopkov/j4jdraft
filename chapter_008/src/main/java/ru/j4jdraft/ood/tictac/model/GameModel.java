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

    /*
    Ввод хода игрока.
    true - выигрыш (игра закончена)
     */
    public boolean move(int playerId, Move move) {
        // поставить символ соответствующего игрока на поле
        int row = move.getRow();
        int col = move.getCol();
        Mark mark = null;   // determine mark
        if (grid.isFreeAt(row, col)) {
            grid.setMark(row, col, mark);
        } else {
            throw new IllegalMoveException("Cell at position " + move + " is not free");
        }
        // проверить не закончена ли игра
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

    public void notifyObservers(GameEvent event) {
        for (GameObserver observer : observers) {
            observer.update(event);
        }
    }
}
