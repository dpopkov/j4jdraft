package ru.j4jdraft.mt.bomberman;

/**
 * Represents position of a cell on board.
 */
public class Cell {
    private final int row;
    private final int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "(" + row + "," + col + ")";
    }

    public Direction directionTo(Cell target) {
        int dX = target.col - col;
        int dY = target.row - row;
        if (dX == 0 ^ dY == 0) {
            return Direction.of(dX, dY);
        }
        return null;
    }

    public Cell inDirection(Direction direction) {
        int row = this.row + direction.getDy();
        int col = this.col + direction.getDx();
        return new Cell(row, col);
    }
}
