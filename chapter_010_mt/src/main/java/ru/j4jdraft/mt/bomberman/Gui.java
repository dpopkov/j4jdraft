package ru.j4jdraft.mt.bomberman;

import ru.j4jdraft.mt.bomberman.gui.LabelState;

public interface Gui {

    /** Occupy the specified cell in GUI. */
    void occupyOnGui(Position position, LabelState state);

    /** Display moving from one cell to another. */
    void updateGui(Position from, Position to);
}
