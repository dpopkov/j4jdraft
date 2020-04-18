package ru.j4jdraft.ood.tictac.model;

import java.util.ArrayList;
import java.util.List;

public class PlayersCarousel {
    private final List<PlayerId> players = new ArrayList<>();
    private int currentIdx;

    public void add(PlayerId player) {
        players.add(player);
    }

    public void setCurrentById(int id) {
        boolean found = false;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getId() == id) {
                currentIdx = i;
                found = true;
                break;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("Can not find player with id: " + id);
        }
    }

    public PlayerId next() {
        PlayerId player = players.get(currentIdx++);
        currentIdx = currentIdx == players.size() ? 0 : currentIdx;
        return player;
    }
}
