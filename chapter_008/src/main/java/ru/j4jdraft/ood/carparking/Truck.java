package ru.j4jdraft.ood.carparking;

public class Truck implements Vehicle {
    private final int size;

    public Truck(int size) {
        this.size = size;
    }

    @Override
    public int size() {
        return size;
    }
}
