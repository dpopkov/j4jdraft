package ru.j4jdraft.ood.mnpoly.spaces;

import ru.j4jdraft.ood.mnpoly.Estate;
import ru.j4jdraft.ood.mnpoly.Player;
import ru.j4jdraft.ood.mnpoly.Space;

public class EstateSpace implements Space {
    private Estate estate;

    @Override
    public void enter(Player player) {
        if (estate.isOwned()) {
            if (player == estate.getOwner()) {
                player.develop(estate);
            } else {
                player.pay(estate.getOwner(), estate.calculateRent());
            }
        } else {
            if (player.wants(estate)) {
                player.buyEstate(estate);
            } else {
                player.allowAuction(estate);
            }
        }
    }
}
