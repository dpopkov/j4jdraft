package ru.j4jdraft.ood.mnpoly.actions;

import ru.j4jdraft.ood.mnpoly.Action;
import ru.j4jdraft.ood.mnpoly.Player;

public class PayToLeaveJailAction implements Action {
    @Override
    public void act(Player player) {
        player.payToBank(50);
    }
}
