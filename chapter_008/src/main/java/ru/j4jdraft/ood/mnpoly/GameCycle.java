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
            Space space = nextSpace(player.getCurrentSpace(), n);
            space.enter(player);
            player = nextPlayer();
        }
    }

    private Space nextSpace(Space currentSpace, int steps) {
        return null;
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
