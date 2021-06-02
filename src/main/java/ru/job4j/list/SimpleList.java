package ru.job4j.list;

public interface SimpleList<E> extends Iterable<E> {

    void add(E value);

    E get(int index);

    void remove(int index);

    int size();
}
