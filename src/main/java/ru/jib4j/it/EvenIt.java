package ru.jib4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator<Integer> {

    private int[] source;
    private int position;

    public EvenIt(int[] src) {
        source = src;
        position = 0;
    }

    @Override
    public boolean hasNext() {
        int i = position;
        while (i < source.length && (source[i] % 2) != 0) {
            i++;
        }
        boolean result = i < source.length;
        if (result) {
            position = i;
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return source[position++];
    }
}
