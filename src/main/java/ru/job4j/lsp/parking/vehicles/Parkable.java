package ru.job4j.lsp.parking.vehicles;

import java.util.Set;

public interface Parkable {

    /**
     * @return Размер парковочного места
     */
    int getParkingSpaceSize();

    void setParkingSpaceSize(int value);

    /**
     *  Получить номера используемых парковочных мест
      * @return множество номеров мест или null
     */
    Set<Integer> getParkingSpaceNumbers();

    /**
     * Записать номера используемых парковочных мест
     * @param numbers - множество номеров мест или null
     */
    void setParkingSpaceNumbers(Set<Integer> numbers);
}
