package ru.job4j.isp;

public interface Menu {

    MenuItem getItemByFullIndex(String itemIndex);

    void addItem(String parentItemIndex, String name);

    void removeItem(String itemIndex);

    void runItemCommand(String itemIndex);

    void print();
}
