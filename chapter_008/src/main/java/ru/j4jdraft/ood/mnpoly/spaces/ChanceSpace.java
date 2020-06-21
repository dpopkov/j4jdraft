package ru.j4jdraft.ood.mnpoly.spaces;

import ru.j4jdraft.ood.mnpoly.Chance;
import ru.j4jdraft.ood.mnpoly.ChanceCard;
import ru.j4jdraft.ood.mnpoly.Player;
import ru.j4jdraft.ood.mnpoly.Space;

public class ChanceSpace implements Space {
    private final Chance chance;

    public ChanceSpace(Chance chance) {
        this.chance = chance;
    }

    @Override
    public void enter(Player player) {
        ChanceCard card = chance.takeTopCard();
        if (Math.random() > 0.5) {
            card.use(player);
            chance.putBack(card);
        } else {
            player.keepCard(card);
        }
    }
}
