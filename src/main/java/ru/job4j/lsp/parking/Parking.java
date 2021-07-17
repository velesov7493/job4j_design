package ru.job4j.lsp.parking;

public interface Parking {

    /**
     * Остаток парковочных мест
     * @param object - для какого транспорта
     * @return количество свободных мест
     */
    int remainingParkingSpaces(Parkable object);

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
