package ru.j4jdraft.ood.tictaccli;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

public class GameCycleTest {
    private final GameGrid grid = mock(GameGrid.class);
    private final Output output = mock(Output.class);
    private final Player first = mock(Player.class);
    private final Player second = mock(Player.class);

    @Test
    public void whenThereIsWinnerThenGameStopsAndCanReturnWinner() {
        when(grid.getWinner(anyInt())).thenReturn(Mark.X);
        GameCycle cycle = new GameCycle(grid, output, first, second, 3);
        cycle.start();
        assertThat(cycle.getWinner(), is(Mark.X));
    }

    @Test
    public void whenCycleStartsThenGridIsPrinted() {
        when(grid.getWinner(anyInt())).thenReturn(Mark.X);
        GameCycle cycle = new GameCycle(grid, output, first, second, 3);
        cycle.start();
        verify(output, times(2)).printGrid(grid);
    }

    @Test
    public void whenCycleStartedThenBothPlayersMakeMoves() {
        when(grid.getWinner(anyInt())).thenReturn(null).thenReturn(Mark.X);
        GameCycle cycle = new GameCycle(grid, output, first, second, 3);
        cycle.start();
        verify(first).makeMove(any(GameGrid.class));
        verify(second).makeMove(any(GameGrid.class));
    }
}
