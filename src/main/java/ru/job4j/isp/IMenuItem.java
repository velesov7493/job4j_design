package ru.job4j.isp;

import java.util.List;

public interface IMenuItem {

    boolean hasChildren();

    void addChild(IMenuItem value);

    void removeChild(int index);

    List<IMenuItem> getChildren();

    String getName();

    void setName(String value);

    ICommand getCommand();

    void setCommand(ICommand value);
}
