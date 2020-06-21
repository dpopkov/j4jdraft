package ru.j4jdraft.ood.mnpoly.spaces;

import ru.j4jdraft.ood.mnpoly.Player;
import ru.j4jdraft.ood.mnpoly.Space;

public class JailSpace implements Space {
    @Override
    public void enter(Player player) {
        if (player.isInJail()) {
            System.out.println("The player is now in jail");
        } else {
            System.out.println("The player is just a visitor in the jail");
        }
    }
}
