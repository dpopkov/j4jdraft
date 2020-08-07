package ru.j4jdraft.mt.bomberman;

import org.junit.Test;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class CellTest {

    @Test
    public void testDirectionTo() {
        Cell cell = new Cell(1, 1);
        assertThat(cell.directionTo(new Cell(0, 1)), is(Direction.UP));
        assertThat(cell.directionTo(new Cell(1, 2)), is(Direction.RIGHT));
        assertThat(cell.directionTo(new Cell(2, 1)), is(Direction.DOWN));
        assertThat(cell.directionTo(new Cell(1, 0)), is(Direction.LEFT));
        assertNull(cell.directionTo(new Cell(0, 0)));
    }
}
