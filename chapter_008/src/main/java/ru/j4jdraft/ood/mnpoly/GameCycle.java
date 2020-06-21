package ru.j4jdraft.ood.mnpoly;

import java.util.ArrayList;
import java.util.List;

public class GameCycle {
    private final Dice dice = new Dice();
    private final List<Player> players = new ArrayList<>();
    private final List<Space> spaces = new ArrayList<>();
    private Jail jail = new Jail();

    public void start() {
        Player player = chooseStartingPlayer();

        while (running()) {
            int n = getSteps(player);
            if (n != -1) {
                Space space = nextSpace(player.getCurrentSpace(), n);
                player.moveTo(space);
                if (player.isBankrupt()) {
                    players.remove(player);
                    System.out.println(player + " is bankrupt and removed from the game!");
                }
            }
            player = nextPlayer();
        }
    }

    private int getSteps(Player player) {
        int count = 0;
        while (count < 3) {
            int n1 = dice.rollOne();
            int n2 = dice.rollOne();
            if (n1 == n2) {
                count++;
            } else {
                return n1 + n2;
            }
        }
        player.putInJail(jail);
        return -1;
    }

    private Space nextSpace(Space currentSpace, int steps) {
        return null;
    }

    private Player chooseStartingPlayer() {
        return null;
    }

    private boolean running() {
        return players.size() > 1;
    }

    private Player nextPlayer() {
        return null;
    }
}
