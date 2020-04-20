package ru.j4jdraft.ood.tictac.model;

import java.util.ArrayList;
import java.util.List;

/*
Позволяет вводить ходы до завершения игры.
Является Observable
 */
public class GameModel {
    private final GameGrid grid;
    private final Grid gridView;
    private boolean finished;
    private final int winLineLength;
    private Mark winner;
    private final List<GameObserver> observers = new ArrayList<>();
    private final PlayersCarousel players = new PlayersCarousel();

    public GameModel(GameGrid grid, int winLineLength) {
        this.grid = grid;
        this.winLineLength = winLineLength;
        gridView = new UnmodifiableGrid(grid);
    }

    public Grid getGrid() {
        return gridView;
    }

    public void addPlayer(PlayerId player) {
        players.add(player);
    }

    public void start(int startingId) {
        players.setCurrentById(startingId);
        notifyObservers(GameEvent.NEXT_MOVE, players.next());
    }

    /**
     * Makes move for the specified player.
     * @param playerId player that tries to make a move
     * @param position position for the move
     * @return true if the move was allowed, false otherwise
     */
    public boolean move(PlayerId playerId, Position position) {
        if (!grid.isFreeAt(position)) {
            return false;
        }
        grid.setMark(position, playerId.getMark());
        GameEvent event = gameFinished() ? GameEvent.GAME_FINISHED : GameEvent.NEXT_MOVE;
        notifyObservers(event, players.next());
        return true;
    }

    private boolean gameFinished() {
        Mark mark = grid.getWinner(winLineLength);
        if (mark != null) {
            finished = true;
            winner = mark;
        }
        return finished;
    }

    public int getWinnerId() {
        if (finished) {
            PlayerId player = players.findBy(p -> p.getMark() == winner);
            if (player != null) {
                return player.getId();
            }
            throw new IllegalStateException("Can not find winner with mark " + winner);
        } else {
            throw new IllegalStateException("Can not get winner because the game is not finished yet");
        }
    }

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(GameEvent event, PlayerId activePlayer) {
        for (GameObserver observer : observers) {
            observer.update(event, activePlayer);
        }
    }
}
