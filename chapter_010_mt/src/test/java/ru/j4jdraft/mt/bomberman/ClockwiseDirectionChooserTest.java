package ru.j4jdraft.mt.bomberman;

import org.junit.Test;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class ClockwiseDirectionChooserTest {

    @Test
    public void whenNextThenAfterUpGoesRight() {
        DirectionChooser chooser = new ClockwiseDirectionChooser(Direction.UP);
        assertThat(chooser.next(), is(Direction.RIGHT));
        assertThat(chooser.next(), is(Direction.DOWN));
        assertThat(chooser.next(), is(Direction.LEFT));
        assertNull(chooser.next());
    }

    @Test
    public void whenNextThenAfterLeftGoesUp() {
        DirectionChooser chooser = new ClockwiseDirectionChooser(Direction.LEFT);
        assertThat(chooser.next(), is(Direction.UP));
        assertThat(chooser.next(), is(Direction.RIGHT));
        assertThat(chooser.next(), is(Direction.DOWN));
        assertNull(chooser.next());
    }
}
