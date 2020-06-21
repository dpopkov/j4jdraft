package ru.j4jdraft.ood.mnpoly.spaces;

import ru.j4jdraft.ood.mnpoly.Estate;
import ru.j4jdraft.ood.mnpoly.Owner;
import ru.j4jdraft.ood.mnpoly.Player;
import ru.j4jdraft.ood.mnpoly.Space;

public class EstateSpace implements Space {
    private Estate estate;

    @Override
    public void enter(Player player) {
        if (estate.isOwned()) {
            Owner owner = estate.getOwner();
            player.payRent(owner, estate.calculateRent());
        } else {
            if (Math.random() > 0.5) {
                player.buy(estate);
            } else {
                player.allowAuction(estate);
            }
        }
    }
}
