package ru.j4jdraft.ood.tictaccli;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class HumanPlayerTest {
    private final GridView grid = mock(GridView.class);
    private final Input input = mock(Input.class);

    @Test
    public void whenMakeMoveThenRequestsPosition() {
        when(input.requestPosition("Enter row and column: ")).thenReturn(new Position(1, 2));
        Player human = new HumanPlayer(Mark.X, input);
        Position pos = human.makeMove(grid);
        verify(input).requestPosition("Enter row and column: ");
        assertThat(pos.getRow(), is(1));
        assertThat(pos.getCol(), is(2));
    }
}
