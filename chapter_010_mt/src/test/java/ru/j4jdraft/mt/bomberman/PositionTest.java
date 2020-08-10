package ru.j4jdraft.mt.bomberman;

import org.junit.Test;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class PositionTest {

    @Test
    public void testDirectionTo() {
        Position position = new Position(1, 1);
        assertThat(position.directionTo(new Position(0, 1)), is(Direction.UP));
        assertThat(position.directionTo(new Position(1, 2)), is(Direction.RIGHT));
        assertThat(position.directionTo(new Position(2, 1)), is(Direction.DOWN));
        assertThat(position.directionTo(new Position(1, 0)), is(Direction.LEFT));
        assertNull(position.directionTo(new Position(0, 0)));
    }
}
