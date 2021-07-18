package ru.job4j.lsp.parking.vehicles;

public class Truck extends AbstractVehicle implements Parkable {

    private int parkingSpaceSize;

    @Override
    public int getParkingSpaceSize() {
        return parkingSpaceSize;
    }

    public void setParkingSpaceSize(int value) {
        parkingSpaceSize = value;
    }
}
