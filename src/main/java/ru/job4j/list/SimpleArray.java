package ru.job4j.list;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {

    final class ElementsIterator implements Iterator<T> {

        private int position;
        private int expectedModCount;

        public ElementsIterator() {
            position = 0;
            expectedModCount = modCount;
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
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return (T) elements[position++];
        }
    }

    private Object[] elements;
    private int size;
    private int capacity;
    private int modCount;

    public SimpleArray() {
        capacity = 16;
        elements = new Object[capacity];
    }

    public SimpleArray(int aCapacity) {
        capacity = aCapacity;
        elements = new Object[capacity];
    }

    private void resize() {
        double fullness = (double) size / capacity;
        if (fullness > 0.0618 && fullness < 1) {
            return;
        }
        if (size == capacity) {
            capacity *= 1.618;
        } else if (fullness < 0.618) {
            capacity = (int) Math.ceil(1.5 * size);
        }
        Object[] newElements = new Object[capacity];
        System.arraycopy(
                elements, 0,
                newElements, 0,
                size
        );
        elements = newElements;
    }

    public void add(T model) {
        elements[size++] = model;
        resize();
        modCount++;
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
        resize();
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) elements[index];
    }

    public Iterator<T> iterator() {
        return new ElementsIterator();
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