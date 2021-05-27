package ru.jib4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {

    private final int[][] data;
    private int row;
    private int column;

    public MatrixIt(int[][] data) {
        this.data = data;
        row = 0;
        column = 0;
    }

    private void nextCell() {
        column++;
        while (
            row < data.length
            && column >= data[row].length
        ) {
            column = 0;
            row++;
        }
    }

    @Override
    public boolean hasNext() {
        if (column >= data[row].length) {
            nextCell();
        }
        return row < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int result = data[row][column];
        nextCell();
        return result;
    }
}
