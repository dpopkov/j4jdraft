package ru.j4jdraft.ood.tictac.model;

import java.util.ArrayList;
import java.util.List;

public class ArrayGrid implements GameGrid {
    private final Mark[][] cells;
    private final List<GridObserver> observers = new ArrayList<>();

    public ArrayGrid(int size) {
        cells = new Mark[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = Mark.EMPTY;
            }
        }
    }

    @Override
    public Mark getMark(Position position) {
        return cells[position.getRow()][position.getCol()];
    }

    @Override
    public void setMark(Position position, Mark mark) {
        if (!isFreeAt(position)) {
            throw new IllegalStateException("This cell is busy");
        }
        cells[position.getRow()][position.getCol()] = mark;
        notifyObservers(position, mark);
    }

    @Override
    public void addObserver(GridObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(Position position, Mark mark) {
        for (GridObserver ob : observers) {
            ob.update(position, mark);
        }
    }

    @Override
    public boolean isFreeAt(Position position) {
        return cells[position.getRow()][position.getCol()] == Mark.EMPTY;
    }

    @Override
    public int size() {
        return cells.length;
    }
}
