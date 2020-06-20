package ru.j4jdraft.ood.mnpoly;

public class ChanceCard {
    private final Action action;

    public ChanceCard(Action action) {
        this.action = action;
    }

    public void use(Player player) {
        action.act(player);
    }
}
