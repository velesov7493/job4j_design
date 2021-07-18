package ru.job4j.lsp.parking;

import ru.job4j.lsp.parking.vehicles.Parkable;

import java.util.*;
import java.util.stream.Collectors;

public class VehiclesParking implements Parking {

    // Map<spaceId, Parkable>
    private Map<Integer, Parkable> vehicles;
    // Map<spaceId, spaceSize>
    private Map<Integer, Integer> parkSpaces;

    /**
     * Конструктор парковки
     * @param structure - карта<РазмерМеста,КоличествоМест>
     */

    public VehiclesParking(Map<Integer, Integer> structure) {
        parkSpaces = new HashMap<>();
        int spaceId = 1;
        for (Map.Entry<Integer, Integer> entry : structure.entrySet()) {
            int size = entry.getKey();
            int count = entry.getValue();
            for (int i = 0; i < count; i++) {
                parkSpaces.put(spaceId++, size);
            }
        }
        vehicles = new HashMap<>();
    }

    private boolean isFreeSpace(int spaceId) {
        return !vehicles.containsKey(spaceId);
    }

    private Set<Integer> continuousSequence(Set<Integer> source, int n) {
        List<Integer> list =
                source.stream().sorted().collect(Collectors.toList());
        Set<Integer> result = new LinkedHashSet<>();
        Iterator<Integer> i = list.iterator();
        int found = 0;
        while (i.hasNext() && found < n) {
            int nextVal = i.next();
            if (source.contains(nextVal + 1) || n == found + 1) {
                result.add(nextVal);
                found++;
            } else {
                result.clear();
                found = 0;
            }
        }
        if (found < n) {
            result.clear();
        }
        return result;
    }

    private Set<Integer> findFreeSingleSpaces(int count) {
        Set<Integer> allSpaces =
                parkSpaces.entrySet().stream()
                .filter((e) -> e.getValue() == 1 && isFreeSpace(e.getKey()))
                .map(Map.Entry::getKey).collect(Collectors.toSet());
        return continuousSequence(allSpaces, count);
    }

    private Set<Integer> findFreeSpaces(int spaceSize) {
        Set<Integer> result = new HashSet<>();
        Optional<Integer> spaceId =
                parkSpaces.entrySet().stream()
                .filter((e) -> e.getValue() == spaceSize && isFreeSpace(e.getKey()))
                .map(Map.Entry::getKey).findFirst();
        if (spaceId.isEmpty()) {
            result = findFreeSingleSpaces(spaceSize);
        } else {
            result.add(spaceId.get());
        }
        return result;
    }

    private int spacesCount(int spaceSize) {
        return findFreeSpaces(spaceSize).size();
    }

    @Override
    public void add(Parkable vehicle) {
        Set<Integer> spaceNumbers = findFreeSpaces(vehicle.getParkingSpaceSize());
        if (spaceNumbers.isEmpty()) {
            throw new IllegalStateException(
                    "Для транспорта размером " + vehicle.getParkingSpaceSize()
                    + " не хватает места!"
            );
        }
        vehicle.setParkingSpaceNumbers(spaceNumbers);
        for (Integer spaceId : spaceNumbers) {
            vehicles.put(spaceId, vehicle);
        }
    }

    @Override
    public void remove(Parkable vehicle) {
        Set<Integer> spaceNumbers = vehicle.getParkingSpaceNumbers();
        if (spaceNumbers == null) {
            throw new IllegalArgumentException(
                    "Нельзя убрать с парковки транспорт, который на ней не припаркован!"
            );
        }
        spaceNumbers.forEach(vehicles::remove);
        vehicle.setParkingSpaceNumbers(null);
    }
}