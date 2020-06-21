package ru.j4jdraft.ood.mnpoly.spaces;

import ru.j4jdraft.ood.mnpoly.Bank;
import ru.j4jdraft.ood.mnpoly.BankNote;
import ru.j4jdraft.ood.mnpoly.Player;
import ru.j4jdraft.ood.mnpoly.Space;

import java.util.List;

/** Стартовый квадрат при повторном прохождении через который игрок получает заплату. */
public class GoSpace implements Space {
    private final Bank bank;

    public GoSpace(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void enter(Player player) {
        List<BankNote> salary = bank.give(200);
        player.take(salary);
    }
}
