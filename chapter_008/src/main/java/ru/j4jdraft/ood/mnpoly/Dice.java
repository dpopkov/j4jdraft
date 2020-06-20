package ru.j4jdraft.ood.mnpoly;

import java.util.Random;

public class Dice {
    private final Random random = new Random();

    public int rollOne() {
        return random.nextInt(6) + 1;
    }

    public int rollTwo() {
        return rollOne() + rollOne();
    }
}
