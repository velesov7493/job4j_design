package ru.job4j.lsp.parking.vehicles;

import java.util.Set;

public abstract class AbstractVehicle implements Parkable {

    private Set<Integer> parkingSpaceNumbers;

    @Override
    public Set<Integer> getParkingSpaceNumbers() {
        return parkingSpaceNumbers;
    }

    @Override
    public void setParkingSpaceNumbers(Set<Integer> numbers) {
        parkingSpaceNumbers = numbers;
    }
}
