package ru.job4j.lsp.parking;

import ru.job4j.lsp.parking.vehicles.Parkable;

public interface Parking {

    /**
     * Добавить транспорт на парковку
     * @param vehicle - паркуемый транспорт
     */
    void add(Parkable vehicle);

    /**
     * Убрать транспорт с парковки
     * @param vehicle - транспорт
     */
    void remove(Parkable vehicle);
}
