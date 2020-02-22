package ru.j4jdraft.ood.carparking;

import java.util.List;

public interface SpaceFinder {
    List<ParkingSpot> find(int size, List<ParkingSpot> allSpots);
}
