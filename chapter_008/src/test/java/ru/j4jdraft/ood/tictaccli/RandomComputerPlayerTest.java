package ru.j4jdraft.ood.tictaccli;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RandomComputerPlayerTest {
    private final GridView gridView = mock(GridView.class);
    private final Output output = mock(Output.class);

    @Test
    public void whenMakeMoveThenUsesFreeCells() {
        when(gridView.isFreeAt(any(Position.class))).thenReturn(true);
        when(gridView.size()).thenReturn(3);
        Player computer = new RandomComputerPlayer(Mark.O, output, 0L);
        Position position = computer.makeMove(gridView);
        assertNotNull(position);
        verify(gridView).isFreeAt(any(Position.class));
    }

    @Test
    public void whenMakeMoveThenOutputsGrid() {
        when(gridView.isFreeAt(any(Position.class))).thenReturn(true);
        Player computer = new RandomComputerPlayer(Mark.O, output, 0L);
        computer.makeMove(gridView);
        verify(output).printGrid(gridView);
    }

    @Test(timeout = 100)
    public void whenMakeMoveOnFullGridThenReturnNull() {
        when(gridView.isFreeAt(any(Position.class))).thenReturn(false);
        when(gridView.size()).thenReturn(3);
        Player computer = new RandomComputerPlayer(Mark.O, output, 0L);
        Position position = computer.makeMove(gridView);
        assertNull(position);
    }
}
