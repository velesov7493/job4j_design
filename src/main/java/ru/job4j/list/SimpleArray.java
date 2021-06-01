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
        if (size == elements.length) {
            capacity = (int) Math.ceil(1.5 * size);
            elements = Arrays.copyOf(elements, capacity);
        }
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
        if (size != that.size) {
            return false;
        }
        boolean result = true;
        for (int i = 0; i < size; i++) {
            if (!Objects.equals(elements[i], that.elements[i])) {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (int i = 0; i < size; i++) {
            result ^= i ^ Objects.hashCode(elements[i]);
        }
        return result;
    }
}