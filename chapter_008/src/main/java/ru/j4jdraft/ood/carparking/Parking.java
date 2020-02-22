package ru.j4jdraft.ood.carparking;

import java.util.List;

public class Parking {
    private List<ParkingSpot> spots;
    private final SpaceFinder finder;

    public Parking(int numPlaces, SpaceFinder finder) {
        spots = initSpots(numPlaces);
        this.finder = finder;
    }

    public Ticket driveIn(Vehicle vehicle) {
        return null;
    }

    public Vehicle leave(Ticket ticket) {
        return null;
    }

    private List<ParkingSpot> initSpots(int numPlaces) {
        return null;
    }
}
