package ru.j4jdraft.ood.mnpoly;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MoneyBagTest {

    @Test
    public void testTake() {
        MoneyBag bag = new MoneyBag();
        assertFalse(bag.hasEnough(1));
        bag.take(List.of(BankNote.ONE));
        assertTrue(bag.hasEnough(1));
        bag.take(List.of(BankNote.TEN));
        assertTrue(bag.hasEnough(11));
        bag.take(List.of(BankNote.TEN));
        assertTrue(bag.hasEnough(21));
    }
}
