package ru.j4jdraft.ood.tictaccli;

public class GameCycle {
    private final GameGrid grid;
    private final Output output;
    private final Player first;
    private final Player second;
    private final int winningLength;
    private Player currentPlayer;
    private Mark winner;

    public GameCycle(GameGrid grid, Output output, Player first, Player second, int winningLength) {
        this.grid = grid;
        this.output = output;
        this.first = first;
        this.second = second;
        this.winningLength = winningLength;
        currentPlayer = first;
    }

    public void start() {
        output.printGrid(grid);
        while (winner == null && !grid.isFull()) {
            Position move = currentPlayer.makeMove(grid);
            if (!grid.isFreeAt(move)) {
                output.print("This cell is busy. ");
            } else {
                grid.setMark(move, currentPlayer.getMark());
                output.printGrid(grid);
                swapPlayers();
                winner = grid.getWinner(winningLength);
            }
        }
    }

    private void swapPlayers() {
        currentPlayer = currentPlayer == first ? second : first;
    }

    public Mark getWinner() {
        return winner;
    }
}
