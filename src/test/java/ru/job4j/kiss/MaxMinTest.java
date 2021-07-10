package ru.job4j.kiss;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void whenMax() {
        List<Integer> in = List.of(1, 2, 3, 5, 8, 13, 21);
        Integer expected = 21;
        MaxMin m = new MaxMin();
        assertEquals(expected, m.max(in, Integer::compareTo));
    }

    @Test
    public void whenMin() {
        List<Integer> in = List.of(21, 34, 55, 89, 144, 233, 377);
        Integer expected = 21;
        MaxMin m = new MaxMin();
        assertEquals(expected, m.min(in, Integer::compareTo));
    }
}