package ru.job4j.lsp.parking.vehicles;

public class Truck extends AbstractVehicle implements ParkableSized {

    private int parkingSpaceSize;

    @Override
    public int getParkingSpaceSize() {
        return parkingSpaceSize;
    }

    @Override
    public void setParkingSpaceSize(int value) {
        parkingSpaceSize = value;
    }
}
