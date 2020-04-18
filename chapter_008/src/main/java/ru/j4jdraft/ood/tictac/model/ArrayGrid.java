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
    public Mark getMark(Position position) {
        return cells[position.getRow()][position.getCol()];
    }

    @Override
    public void setMark(Position position, Mark mark) {
        if (!isFreeAt(position)) {
            throw new IllegalStateException("This cell is busy");
        }
        cells[position.getRow()][position.getCol()] = mark;
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
