package ru.job4j.lsp.parking.vehicles;

public class Car extends AbstractVehicle implements Parkable {

    @Override
    public int getParkingSpaceSize() {
        return 1;
    }
}
