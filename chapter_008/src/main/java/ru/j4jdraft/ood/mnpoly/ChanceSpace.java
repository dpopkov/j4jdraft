package ru.j4jdraft.ood.mnpoly;

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
