package ru.job4j.isp;

import java.util.ArrayList;
import java.util.List;

public class SimpleMenuItem implements MenuItem {

    private String name;
    private List<MenuItem> children;
    private Command command;

    public SimpleMenuItem(String aName) {
        name = aName;
        children = new ArrayList<>();
    }

    @Override
    public boolean hasChildren() {
        return !children.isEmpty();
    }

    @Override
    public void addChild(MenuItem value) {
        children.add(value);
    }

    @Override
    public void removeChild(int index) {
        children.remove(index);
    }

    @Override
    public List<MenuItem> getChildren() {
        return new ArrayList<>(children);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String value) {
        name = value;
    }

    @Override
    public Command getCommand() {
        return command;
    }

    @Override
    public void setCommand(Command value) {
        command = value;
    }
}
