package ru.j4jdraft.ood.tictac.model;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class ArrayGridTest {

    @Test
    public void whenInitializedThenCellsAreFree() {
        GameGrid grid = new ArrayGrid(3);
        assertTrue(grid.isFreeAt(new Position(0, 2)));
        assertTrue(grid.isFreeAt(new Position(2, 0)));
    }

    @Test(expected = IllegalStateException.class)
    public void whenSettingBusyCellThenException() {
        GameGrid grid = new ArrayGrid(3);
        grid.setMark(new Position(2, 2), Mark.X);
        assertThat(grid.getMark(new Position(2, 2)), is(Mark.X));
        grid.setMark(new Position(2, 2), Mark.O);
    }
}
