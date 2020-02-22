package ru.j4jdraft.ood.carparking;

public class ParkingSpot {
    private long id;
    private Vehicle parkedVehicle;

    public boolean isFree() {
        return true;
    }

    public void occupyBy(Vehicle vehicle) {

    }

    public Vehicle freeUp() {
        return null;
    }
}
