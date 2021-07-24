package ru.job4j.isp;

import ru.job4j.isp.io.Output;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class SimpleMenu implements Menu {

    private Output output;
    private List<MenuItem> items;

    public SimpleMenu(Output aOutput) {
        output = aOutput;
        items = new ArrayList<>();
    }

    private int[] getParentPath(int[] path) {
        return Arrays.copyOf(path, path.length - 1);
    }

    private int[] getPath(String index) {
        String[] numbers = index.split("\\.");
        int l = numbers.length == 0 ? 1 : numbers.length;
        int[] indexes = new int[l];
        if (numbers.length == 0) {
            indexes[0] = Integer.parseInt(index) - 1;
            return indexes;
        }
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = Integer.parseInt(numbers[i]) - 1;
        }
        return indexes;
    }

    private MenuItem getItemByPath(int[] indexes) {
        MenuItem item = items.get(indexes[0]);
        for (int i = 1; i < indexes.length; i++) {
            if (!item.hasChildren()) {
                throw new NoSuchElementException();
            }
            item = item.getChildren().get(indexes[i]);
        }
        return item;
    }

    private String sprint(MenuItem source, int level, String index) {
        return
                index + "\t"
                + "\t".repeat(level)
                + source.getName();
    }

    private List<String> sprintMenu(List<MenuItem> menu, int level, String prefix) {
        List<String> result = new ArrayList<>();
        int number = 1;
        for (MenuItem item : menu) {
            String lindex = prefix + number + ".";
            result.add(sprint(item, level, lindex));
            if (item.hasChildren()) {
                result.addAll(sprintMenu(item.getChildren(), level + 1, lindex));
            }
            number++;
        }
        return result;
    }

    @Override
    public MenuItem getItemByFullIndex(String index) {
        if (index == null || index.isEmpty()) {
            return null;
        }
        int[] indexes = getPath(index);
        return getItemByPath(indexes);
    }

    @Override
    public void addItem(String parentFullIndex, String name) {
        MenuItem parent = getItemByFullIndex(parentFullIndex);
        if (parent == null) {
            items.add(new SimpleMenuItem(name));
        } else {
            parent.addChild(new SimpleMenuItem(name));
        }
    }

    @Override
    public void removeItem(String fullIndex) {
        int[] indexes = getPath(fullIndex);
        MenuItem parent = getItemByPath(getParentPath(indexes));
        parent.removeChild(indexes[indexes.length - 1]);
    }

    @Override
    public void runItemCommand(String itemIndex) {
        MenuItem item = getItemByFullIndex(itemIndex);
        Command c = item.getCommand();
        if (c != null) {
            c.execute();
        }
    }

    @Override
    public void print() {
        List<String> out = sprintMenu(items, 0, "");
        out.forEach(output::println);
    }
}
