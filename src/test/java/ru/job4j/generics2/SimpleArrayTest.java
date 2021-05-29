package ru.job4j.generics2;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void whenAdd() {
        SimpleArray<String> sa = new SimpleArray<>(2);
        String expected = "Test";
        sa.add(expected);
        assertEquals(expected, sa.get(0));
    }

    @Test(expected = IllegalStateException.class)
    public void whenOverflow() {
        SimpleArray<Integer> sa = new SimpleArray<>(2);
        sa.add(1);
        sa.add(2);
        sa.add(3);
    }

    @Test
    public void whenSet() {
        SimpleArray<String> sa = new SimpleArray<>(5);
        String expected = "Test 1";
        sa.add("Что-нибудь");
        sa.set(0, expected);
        assertEquals(expected, sa.get(0));
    }

    @Test
    public void whenRemove() {
        SimpleArray<Integer> sa1 = new SimpleArray<>(15);
        sa1.add(1);
        sa1.add(2);
        sa1.add(3);
        sa1.add(4);
        sa1.add(5);
        sa1.add(6);
        sa1.add(7);
        sa1.add(8);
        sa1.add(9);
        SimpleArray<Integer> sa2 = new SimpleArray<>(15);
        sa2.add(1);
        sa2.add(3);
        sa2.add(4);
        sa2.add(5);
        sa2.add(6);
        sa2.add(7);
        sa2.add(8);
        sa2.add(9);
        sa1.remove(1);
        assertTrue(sa1.equals(sa2));
    }

    @Test
    public void whenMultiRemove() {
        SimpleArray<Integer> sa1 = new SimpleArray<>(15);
        sa1.add(1);
        sa1.add(2);
        sa1.add(3);
        sa1.add(4);
        sa1.add(5);
        sa1.add(6);
        sa1.add(7);
        sa1.add(8);
        sa1.add(9);
        SimpleArray<Integer> sa2 = new SimpleArray<>(15);
        sa2.add(1);
        sa2.add(9);
        for (int i = 1; i <= 7; i++) {
            sa1.remove(1);
        }
        assertTrue(sa1.equals(sa2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenOutOfBounds() {
        SimpleArray<Integer> sa = new SimpleArray<>(5);
        sa.add(1);
        sa.add(2);
        sa.add(3);
        int result = sa.get(3);
    }

    @Test
    public void whenIteratorHasNext() {
        SimpleArray<Integer> sa = new SimpleArray<>(3);
        sa.add(1);
        sa.add(2);
        sa.add(3);
        Iterator<Integer> it = sa.iterator();
        assertTrue(it.hasNext());
    }

    @Test
    public void whenIteratorNoElements() {
        SimpleArray<Integer> sa = new SimpleArray<>(3);
        Iterator<Integer> it = sa.iterator();
        assertFalse(it.hasNext());
    }

    @Test
    public void whenNext() {
        Integer expected = 1;
        SimpleArray<Integer> sa = new SimpleArray<>(3);
        sa.add(1);
        sa.add(2);
        sa.add(3);
        Iterator<Integer> it = sa.iterator();
        assertEquals(expected, it.next());
    }
}