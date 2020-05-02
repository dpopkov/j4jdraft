package ru.j4jdraft.ood.tictaccli;

public class GameCycle {
    private final GameGrid grid;
    private final Player first;
    private final Player second;
    private final int winningLength;
    private Player current;
    private Mark winner;

    public GameCycle(GameGrid grid, Player first, Player second, int winningLength) {
        this.grid = grid;
        this.first = first;
        this.second = second;
        this.winningLength = winningLength;
        current = first;
    }

    public void start() {
        boolean playing = true;
        while (playing) {
            Position move = current.makeMove(grid);
            grid.setMark(move, current.getMark());
            changeCurrentPlayer();
            winner = grid.getWinner(winningLength);
            playing = winner == null;
        }
    }

    private void changeCurrentPlayer() {
        current = current == first ? second : first;
    }

    public Mark getWinner() {
        return winner;
    }
}
