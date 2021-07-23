package ru.job4j.isp;

import java.util.ArrayList;
import java.util.List;

public class SimpleMenuItem implements IMenuItem {

    private String name;
    private List<IMenuItem> children;
    private ICommand command;

    public SimpleMenuItem(String aName) {
        name = aName;
        children = new ArrayList<>();
    }

    @Override
    public boolean hasChildren() {
        return !children.isEmpty();
    }

    @Override
    public void addChild(IMenuItem value) {
        children.add(value);
    }

    @Override
    public void removeChild(int index) {
        children.remove(index);
    }

    @Override
    public List<IMenuItem> getChildren() {
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
    public ICommand getCommand() {
        return command;
    }

    @Override
    public void setCommand(ICommand value) {
        command = value;
    }
}
