package ru.job4j.it;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FlatMap<T> implements Iterator<T> {

    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor;

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
        cursor = data.hasNext() ? data.next() : Collections.emptyIterator();
    }

    @Override
    public boolean hasNext() {
        boolean result = cursor.hasNext();
        while (!result && data.hasNext()) {
            cursor = data.next();
            result = cursor.hasNext();
        }
        return result;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return cursor.next();
    }
}
