package ru.j4jdraft.ood.tictaccli;

public interface GameGrid extends GridView {
    void setMark(Position position, Mark mark);
    Mark getWinner(int lineLength);
}
