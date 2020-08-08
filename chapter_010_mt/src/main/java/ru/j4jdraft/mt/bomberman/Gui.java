package ru.j4jdraft.mt.bomberman;

import ru.j4jdraft.mt.bomberman.gui.LabelState;

public interface Gui {

    /** Occupy the specified cell in GUI. */
    void occupyOnGui(Cell cell, LabelState state);

    /** Display moving from one cell to another. */
    void updateGui(Cell from, Cell to);
}
