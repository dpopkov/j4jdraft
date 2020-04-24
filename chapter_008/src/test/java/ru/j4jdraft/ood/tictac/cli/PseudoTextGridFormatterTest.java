package ru.j4jdraft.ood.tictac.cli;

import org.junit.Test;
import ru.j4jdraft.ood.tictac.model.Grid;
import ru.j4jdraft.ood.tictac.model.Mark;
import ru.j4jdraft.ood.tictac.model.Position;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PseudoTextGridFormatterTest {
    public final static String NL = System.lineSeparator();

    @Test
    public void whenFormatEmptyGridThenReturnBlankCells() {
        Grid grid = mock(Grid.class);
        when(grid.getMark(any())).thenReturn(Mark.EMPTY);
        when(grid.size()).thenReturn(2);
        String expected = String.join(NL,
                "┌───┬───┐",
                "│   │   │",
                "├───┼───┤",
                "│   │   │",
                "└───┴───┘", "");
        GridFormatter formatter = new PseudoTextGridFormatter();
        String result = formatter.format(grid);
        assertThat(result, is(expected));
    }

    @Test
    public void whenFormatNonEmptyGridThenReturnActualCellsRepresentation() {
        Grid grid = mock(Grid.class);
        when(grid.getMark(new Position(1, 0))).thenReturn(Mark.EMPTY);
        when(grid.getMark(new Position(0, 1))).thenReturn(Mark.EMPTY);
        when(grid.getMark(new Position(0, 0))).thenReturn(Mark.X);
        when(grid.getMark(new Position(1, 1))).thenReturn(Mark.O);
        when(grid.size()).thenReturn(2);
        String expected = String.join(NL,
                "┌───┬───┐",
                "│ X │   │",
                "├───┼───┤",
                "│   │ O │",
                "└───┴───┘", "");
        GridFormatter formatter = new PseudoTextGridFormatter();
        String result = formatter.format(grid);
        assertThat(result, is(expected));
    }
}
