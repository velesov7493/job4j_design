package ru.job4j.it;

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

    private boolean endOfLine() {
        return row < data.length && column >= data[row].length;
    }

    private boolean endOfMatrix() {
        return row >= data.length;
    }

    /**
     * Проверка ячейки под указателем [row,column] на конец строки
     * Если строка закончилась, а матрица - нет, то сдвинуть указатель на начало новой строки
     */
    private void checkEOL() {
        while (endOfLine()) {
            column = 0;
            row++;
        }
    }

    @Override
    public boolean hasNext() {
        checkEOL();
        return !endOfMatrix();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}