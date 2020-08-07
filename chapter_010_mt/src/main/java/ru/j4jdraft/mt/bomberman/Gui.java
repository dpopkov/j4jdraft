package ru.j4jdraft.mt.bomberman;

public interface Gui {

    /** Occupy the specified cell in GUI. */
    void occupyOnGui(Cell cell);

    /** Display moving from one cell to another. */
    void updateGui(Cell from, Cell to);
}
