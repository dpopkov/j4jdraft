package ru.j4jdraft.ood.mnpoly;

import ru.j4jdraft.ood.mnpoly.spaces.JailSpace;

public class Jail {
    private Player player;
    private JailSpace jailSpace;

    public void putInside(Player player) {
        this.player = player;
        jailSpace.enter(player);
    }
}
