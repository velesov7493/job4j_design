package ru.job4j.set;

import ru.job4j.list.SimpleArray;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements SSet<T> {

    private SimpleArray<T> set;

    public SimpleSet() {
        set = new SimpleArray<>();
    }

    @Override
    public boolean add(T value) {
        boolean result = !contains(value);
        if (result) {
            set.add(value);
        }
        return result;
    }

    @Override
    public boolean contains(T value) {
        boolean result = false;
        Iterator<T> i = iterator();
        while (i.hasNext()) {
            T entry = i.next();
            if (Objects.equals(entry, value)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public Iterator<T> iterator() {
        return set.iterator();
    }
}
