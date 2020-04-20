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
    public Mark getWinner(int lineLength) {
        Mark result = checkVerticalsAndHorizontals(lineLength);
        if (result != null) {
            return result;
        }
        return checkSlopes(lineLength);
    }

    private Mark checkVerticalsAndHorizontals(int lineLength) {
        for (int i = 0; i < cells.length; i++) {
            Mark vMark = cells[0][i];
            int vertCount = 1;
            Mark horMark = cells[i][0];
            int horCount = 1;
            for (int j = 1; j < cells.length; j++) {
                if (cells[j][i] == vMark) {
                    vertCount++;
                    if (vertCount == lineLength && vMark != Mark.EMPTY) {
                        return vMark;
                    }
                } else {
                    vMark = cells[j][i];
                    vertCount = 1;
                }
                if (cells[i][j] == horMark) {
                    horCount++;
                    if (horCount == lineLength && horMark != Mark.EMPTY) {
                        return horMark;
                    }
                } else {
                    horMark = cells[i][j];
                    horCount = 1;
                }
            }
        }
        return null;
    }

    private Mark checkSlopes(int lineLength) {
        Mark m = checkAllLeftSlopes(lineLength);
        if (m != null) {
            return m;
        }
        return checkAllRightSlopes(lineLength);
    }

    private Mark checkAllLeftSlopes(int lineLength) {
        int stop = cells.length - lineLength;
        for (int r = 0; r <= stop; r++) {
            for (int c = 0; c <= stop; c++) {
                Mark m = checkOneLeftSlope(r, c, lineLength);
                if (m != null) {
                    return m;
                }
            }
        }
        return null;
    }

    private Mark checkAllRightSlopes(int lineLength) {
        int stop = cells.length - lineLength;
        for (int r = 0; r <= stop; r++) {
            for (int c = lineLength - 1; c < cells.length; c++) {
                Mark m = checkOneRightSlope(r, c, lineLength);
                if (m != null) {
                    return m;
                }
            }
        }
        return null;
    }

    private Mark checkOneRightSlope(int r, int c, int lineLength) {
        Mark m = cells[r][c];
        int count = 1;
        for (int i = r + 1, j = c - 1; i < cells.length && j >= 0; i++, j--) {
            if (m != cells[i][j]) {
                m = cells[i][j];
                count = 1;
            } else {
                count++;
                if (count == lineLength && m != Mark.EMPTY) {
                    return m;
                }
            }
        }
        return null;
    }

    private Mark checkOneLeftSlope(int r, int c, int lineLength) {
        Mark m = cells[r][c];
        int count = 1;
        for (int i = r + 1, j = c + 1; i < cells.length && j < cells.length; i++, j++) {
            if (m != cells[i][j]) {
                m = cells[i][j];
                count = 1;
            } else {
                count++;
                if (count == lineLength && m != Mark.EMPTY) {
                    return m;
                }
            }
        }
        return null;
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
