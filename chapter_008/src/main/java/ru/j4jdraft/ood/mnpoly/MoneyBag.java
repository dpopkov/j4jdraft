package ru.j4jdraft.ood.mnpoly;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class MoneyBag {
    private final EnumMap<BankNote, Integer> map = new EnumMap<>(BankNote.class);

    public boolean hasEnough(int value) {
        return value <= totalValue();
    }

    public List<BankNote> give(int value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void take(List<BankNote> bankNotes) {
        for (BankNote bn : bankNotes) {
            map.merge(bn, 1, (oldValue, i2) -> oldValue + 1);
        }
    }

    private int totalValue() {
        int total = 0;
        for (Map.Entry<BankNote, Integer> e : map.entrySet()) {
            total += e.getKey().value() * e.getValue();
        }
        return total;
    }
}
