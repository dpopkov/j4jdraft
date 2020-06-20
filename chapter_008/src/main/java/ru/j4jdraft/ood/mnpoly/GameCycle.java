package ru.j4jdraft.ood.mnpoly;

import java.util.ArrayList;
import java.util.List;

public class GameCycle {
    private final List<Player> players = new ArrayList<>();
    private final List<Space> spaces = new ArrayList<>();

    public void start() {
        Player player = chooseStartingPlayer();

        Dice dice = new Dice();
        while (running()) {
            int n = dice.rollTwo();
            int nextPosition = (player.position() + n) % spaces.size();
            Space space = spaces.get(nextPosition);
            space.enter(player);
            player = nextPlayer();
        }
    }

    private Player chooseStartingPlayer() {
        return null;
    }

    private boolean running() {
        return false;
    }

    private Player nextPlayer() {
        return null;
    }
}
