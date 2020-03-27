package ru.j4jdraft.ood.tictac.model;

public class ArrayGrid implements GameGrid {
    private final Mark[][] cells;

    public ArrayGrid(int size) {
        cells = new Mark[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = Mark.EMPTY;
            }
        }
    }

    @Override
    public Mark getMark(int row, int col) {
        return cells[row][col];
    }

    @Override
    public void setMark(int row, int col, Mark mark) {
        if (!isFreeAt(row, col)) {
            throw new IllegalStateException("This cell is busy");
        }
        cells[row][col] = mark;
    }

    @Override
    public boolean isFreeAt(int row, int col) {
        return cells[row][col] == Mark.EMPTY;
    }

    @Override
    public int size() {
        return cells.length;
    }
}
