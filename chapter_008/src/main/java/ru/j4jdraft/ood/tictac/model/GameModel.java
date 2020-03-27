package ru.j4jdraft.ood.tictac.model;

/*
Позволяет вводить ходы до завершения игры.
Является Observable
 */
public class GameModel {
    public GameModel(GameGrid grid) {

    }

    /*
    Ввод хода игрока.
    true - выигрыш
     */
    public boolean move(int playerId, Move move) {
        return false;
    }
}
