package ru.job4j.isp;

import java.util.List;

public interface MenuItem {

    boolean hasChildren();

    void addChild(MenuItem value);

    void removeChild(int index);

    List<MenuItem> getChildren();

    String getName();

    void setName(String value);

    Command getCommand();

    void setCommand(Command value);
}
