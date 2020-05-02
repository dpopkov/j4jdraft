package ru.j4jdraft.ood.tictaccli;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

public class GameCycleTest {

    @Test
    public void whenThereIsWinnerThenGameStopsAndCanReturnWinner() {
        GameGrid grid = mock(GameGrid.class);
        when(grid.getWinner(anyInt())).thenReturn(Mark.X);
        Player player1 = mock(Player.class);
        GameCycle cycle = new GameCycle(grid, null, null, 3);
        cycle.start();
        assertThat(cycle.getWinner(), is(Mark.X));
    }

    @Test
    public void whenCycleStartedThenBothPlayersMakeMoves() {
        GameGrid grid = mock(GameGrid.class);
        Player first = mock(Player.class);
        Player second = mock(Player.class);
        GameCycle cycle = new GameCycle(grid, first, second, 3);
        cycle.start();
        verify(first).makeMove(any(GameGrid.class));
        verify(second).makeMove(any(GameGrid.class));
    }
}
