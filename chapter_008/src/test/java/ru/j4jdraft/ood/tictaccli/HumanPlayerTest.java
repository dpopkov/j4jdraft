package ru.j4jdraft.ood.tictaccli;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class HumanPlayerTest {
    private final GridView grid = mock(GridView.class);
    private final Output output = mock(Output.class);
    private final Input input = mock(Input.class);

    @Test
    public void whenMakeMoveThenDisplaysGrid() {
        when(input.requestPosition(anyString())).thenReturn(new Position(3, 4));
        Player human = new HumanPlayer(Mark.X, output, input);
        human.makeMove(grid);
        verify(output).printGrid(grid);
    }

    @Test
    public void whenMakeMoveThenRequestsPosition() {
        when(input.requestPosition("Enter row and column: ")).thenReturn(new Position(1, 2));
        Player human = new HumanPlayer(Mark.X, output, input);
        Position pos = human.makeMove(grid);
        verify(input).requestPosition("Enter row and column: ");
        assertThat(pos.getRow(), is(1));
        assertThat(pos.getCol(), is(2));
    }
}
