package ru.job4j.generics3;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class MemStoreTest {

    @Test
    public void whenAdd() {
        MemStore<User> ms = new MemStore<>();
        User in = new User("0001", "Иванов И.И.");
        ms.add(in);
        assertEquals(in, ms.findById("0001"));
    }

    @Test
    public void whenReplace() {
        User in1 = new User("0001", "Иванов И.И.");
        User in2 = new User("0002", "Петров В.Н.");
        User in3 = new User("0001", "Баширов В.В.");
        MemStore<User> ms1 = new MemStore<>();
        MemStore<User> ms2 = new MemStore<>();
        ms1.add(in1);
        ms1.add(in2);
        ms1.replace("0001", in3);
        ms2.add(in2);
        ms2.add(in3);
        assertEquals(ms2, ms1);
    }

    @Test
    public void whenDelete() {
        User in1 = new User("0001", "Иванов И.И.");
        User in2 = new User("0002", "Петров В.Н.");
        User in3 = new User("0003", "Баширов В.В.");
        MemStore<User> ms1 = new MemStore<>();
        MemStore<User> ms2 = new MemStore<>();
        ms1.add(in1);
        ms1.add(in2);
        ms1.add(in3);
        ms2.add(in2);
        ms2.add(in3);
        ms1.delete("0001");
        assertEquals(ms2, ms1);
    }

    @Test
    public void whenFindSuccess() {
        User in1 = new User("0001", "Иванов И.И.");
        User in2 = new User("0002", "Петров В.Н.");
        User in3 = new User("0003", "Баширов В.В.");
        MemStore<User> ms1 = new MemStore<>();
        ms1.add(in1);
        ms1.add(in2);
        ms1.add(in3);
        assertEquals(in2, ms1.findById("0002"));
    }

    @Test
    public void whenFindFail() {
        MemStore<User> ms1 = new MemStore<>();
        User in2 = new User("0002", "Петров В.Н.");
        ms1.add(in2);
        assertNull(ms1.findById("0003"));
    }
}