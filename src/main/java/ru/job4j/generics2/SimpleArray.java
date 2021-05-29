package ru.job4j.generics2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterator<T> {

    private Object[] elements;
    private int position;
    private int size;

    public SimpleArray(int aMaxSize) {
        size = 0;
        elements = new Object[aMaxSize];
    }

    public void add(T model) {
        if (size == elements.length) {
            throw new IllegalStateException("Коллекция переполнена.");
        }
        elements[size++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, size);
        elements[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        if (size - index > 1) {
            System.arraycopy(
                    elements, index + 1,
                    elements, index,
                    size - index - 1
            );
        }
        elements[--size] = null;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) elements[index];
    }

    public Iterator<T> iterator() {
        position = 0;
        return this;
    }

    @Override
    public boolean hasNext() {
        return position < size;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return (T) elements[position++];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleArray<?> that = (SimpleArray<?>) o;
        return Arrays.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }
}
