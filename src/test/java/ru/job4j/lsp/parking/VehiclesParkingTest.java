package ru.job4j.lsp.parking;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import ru.job4j.lsp.parking.vehicles.Car;
import ru.job4j.lsp.parking.vehicles.Parkable;
import ru.job4j.lsp.parking.vehicles.Truck;

import java.util.*;

import static org.junit.Assert.*;

public class VehiclesParkingTest {

    @Test
    public void whenManyCarParksThenSuccess() {
        Map<Integer, Integer> structure = new HashMap<>();
        structure.put(1, 5);
        List<Parkable> cars = List.of(
                new Car(),
                new Car(),
                new Car()
        );
        Parking parking = new VehiclesParking(structure);
        cars.forEach(parking::add);
        assertThat(cars.get(0).getParkingSpaceNumbers(), is(Set.of(1)));
        assertThat(cars.get(1).getParkingSpaceNumbers(), is(Set.of(2)));
        assertThat(cars.get(2).getParkingSpaceNumbers(), is(Set.of(3)));
    }

    @Test(expected = IllegalStateException.class)
    public void whenManyCarParksThenNoSpace() {
        Map<Integer, Integer> structure = new HashMap<>();
        structure.put(1, 5);
        List<Parkable> cars = List.of(
                new Car(), new Car(), new Car(),
                new Car(), new Car(), new Car()
        );
        Parking parking = new VehiclesParking(structure);
        cars.forEach(parking::add);
    }

    @Test
    public void whenTruckParksToSingleSpacesThenSuccess() {
        Map<Integer, Integer> structure = new HashMap<>();
        structure.put(1, 5);
        Parkable truck = new Truck();
        truck.setParkingSpaceSize(5);
        Parking parking = new VehiclesParking(structure);
        parking.add(truck);
        assertThat(truck.getParkingSpaceNumbers(), is(Set.of(1, 2, 3, 4, 5)));
    }

    @Test
    public void when3TrucksToSingleAnd3TrucksToLargeThenSuccess() {
        Map<Integer, Integer> structure = new LinkedHashMap<>();
        structure.put(5, 3);
        structure.put(1, 15);
        List<Parkable> trucks = List.of(
                new Truck(), new Truck(), new Truck(),
                new Truck(), new Truck(), new Truck()
        );
        trucks.forEach((t) -> t.setParkingSpaceSize(5));
        Parking parking = new VehiclesParking(structure);
        trucks.forEach(parking::add);
        assertThat(trucks.get(0).getParkingSpaceNumbers(), is(Set.of(1)));
        assertThat(trucks.get(1).getParkingSpaceNumbers(), is(Set.of(2)));
        assertThat(trucks.get(2).getParkingSpaceNumbers(), is(Set.of(3)));
        assertThat(trucks.get(3).getParkingSpaceNumbers(), is(Set.of(4, 5, 6, 7, 8)));
        assertThat(trucks.get(4).getParkingSpaceNumbers(), is(Set.of(9, 10, 11, 12, 13)));
        assertThat(trucks.get(5).getParkingSpaceNumbers(), is(Set.of(14, 15, 16, 17, 18)));
    }

    @Test
    public void when2TrucksToSingleAfterRemoving2CarsThenSuccess() {
        Map<Integer, Integer> structure = new HashMap<>();
        structure.put(1, 10);
        List<Parkable> trucks = List.of(
                new Truck(), new Truck()
        );
        List<Parkable> cars = List.of(
                new Car(), new Car()
        );
        trucks.forEach((t) -> t.setParkingSpaceSize(5));
        Parking parking = new VehiclesParking(structure);
        cars.forEach(parking::add);
        assertThat(cars.get(0).getParkingSpaceNumbers(), is(Set.of(1)));
        assertThat(cars.get(1).getParkingSpaceNumbers(), is(Set.of(2)));
        cars.forEach(parking::remove);
        trucks.forEach(parking::add);
        assertThat(trucks.get(0).getParkingSpaceNumbers(), is(Set.of(1, 2, 3, 4, 5)));
        assertThat(trucks.get(1).getParkingSpaceNumbers(), is(Set.of(6, 7, 8, 9, 10)));
    }

    @Test(expected = IllegalStateException.class)
    public void when2TrucksToSingleAfter2CarsThenNoSpace() {
        Map<Integer, Integer> structure = new HashMap<>();
        structure.put(1, 10);
        List<Parkable> trucks = List.of(
                new Truck(), new Truck()
        );
        List<Parkable> cars = List.of(
                new Car(), new Car()
        );
        trucks.forEach((t) -> t.setParkingSpaceSize(5));
        Parking parking = new VehiclesParking(structure);
        cars.forEach(parking::add);
        trucks.forEach(parking::add);
    }

    @Test
    public void when2Trucks5and2Trucks4and2Trucks3And2Cars() {
        Map<Integer, Integer> structure = new LinkedHashMap<>();
        structure.put(1, 2);
        structure.put(5, 2);
        structure.put(4, 2);
        structure.put(3, 2);
        List<Parkable> vehicles = List.of(
                new Truck(), new Truck(),
                new Truck(), new Truck(),
                new Truck(), new Truck(),
                new Car(), new Car()
        );
        vehicles.get(0).setParkingSpaceSize(5);
        vehicles.get(1).setParkingSpaceSize(5);
        vehicles.get(2).setParkingSpaceSize(4);
        vehicles.get(3).setParkingSpaceSize(4);
        vehicles.get(4).setParkingSpaceSize(3);
        vehicles.get(5).setParkingSpaceSize(3);
        Parking parking = new VehiclesParking(structure);
        vehicles.forEach(parking::add);
        assertThat(vehicles.get(6).getParkingSpaceNumbers(), is(Set.of(1)));
        assertThat(vehicles.get(7).getParkingSpaceNumbers(), is(Set.of(2)));
        assertThat(vehicles.get(0).getParkingSpaceNumbers(), is(Set.of(3)));
        assertThat(vehicles.get(1).getParkingSpaceNumbers(), is(Set.of(4)));
        assertThat(vehicles.get(2).getParkingSpaceNumbers(), is(Set.of(5)));
        assertThat(vehicles.get(3).getParkingSpaceNumbers(), is(Set.of(6)));
        assertThat(vehicles.get(4).getParkingSpaceNumbers(), is(Set.of(7)));
        assertThat(vehicles.get(5).getParkingSpaceNumbers(), is(Set.of(8)));
    }
}