package ru.j4jdraft.ood.mnpoly.chances;

import ru.j4jdraft.ood.mnpoly.Action;
import ru.j4jdraft.ood.mnpoly.BankNote;
import ru.j4jdraft.ood.mnpoly.Player;

import java.util.List;

public class PayAllPlayersAction implements Action {
    private final List<Player> allPlayers;
    private final int amountToPay;

    public PayAllPlayersAction(List<Player> allPlayers, int amountToPay) {
        this.allPlayers = allPlayers;
        this.amountToPay = amountToPay;
    }

    @Override
    public void act(Player player) {
        for (Player p : allPlayers) {
            if (p != player) {
                List<BankNote> bankNotes = player.give(amountToPay);
                p.take(bankNotes);
            }
        }
    }
}
