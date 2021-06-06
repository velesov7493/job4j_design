package ru.job4j.exam.struct;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AnalizeTest {

    @Test
    public void when1Changed1Deleted() {
        List<Analize.User> prev = List.of(
                new Analize.User(1, "Иванов Иван Иванович"),
                new Analize.User(2, "Петров Василий Иванович"),
                new Analize.User(3, "Баширов Валерий Викторович")
        );
        List<Analize.User> curr = List.of(
                new Analize.User(2, "Петров Василий Николаевич"),
                new Analize.User(3, "Баширов Валерий Викторович")
        );
        Analize a = new Analize();
        Analize.Info info = a.diff(prev, curr);
        assertEquals(1, info.getChanged());
        assertEquals(1, info.getDeleted());
    }

    @Test
    public void when2Changed2Deleted2Added() {
        List<Analize.User> prev = List.of(
                new Analize.User(1, "Иванов Иван Иванович"),
                new Analize.User(2, "Петров Василий Иванович"),
                new Analize.User(3, "Баширов Валерий Викторович"),
                new Analize.User(4, "Потапов Михаил Евгеньевич")
        );
        List<Analize.User> curr = List.of(
                new Analize.User(2, "Петров Василий Николаевич"),
                new Analize.User(3, "Баширов Валерий Николаевич"),
                new Analize.User(5, "Одинцов Сергей Павлович"),
                new Analize.User(6, "Субботин Дмитрий Семенович")
        );
        Analize a = new Analize();
        Analize.Info info = a.diff(prev, curr);
        assertEquals(2, info.getChanged());
        assertEquals(2, info.getDeleted());
        assertEquals(2, info.getAdded());
    }

    @Test
    public void whenNoChanges() {
        List<Analize.User> prev = List.of(
                new Analize.User(1, "Иванов Иван Иванович"),
                new Analize.User(2, "Петров Василий Иванович"),
                new Analize.User(3, "Баширов Валерий Викторович"),
                new Analize.User(4, "Потапов Михаил Евгеньевич")
        );
        List<Analize.User> curr = List.of(
                new Analize.User(1, "Иванов Иван Иванович"),
                new Analize.User(2, "Петров Василий Иванович"),
                new Analize.User(3, "Баширов Валерий Викторович"),
                new Analize.User(4, "Потапов Михаил Евгеньевич")
        );
        Analize a = new Analize();
        Analize.Info info = a.diff(prev, curr);
        assertEquals(0, info.getChanged());
        assertEquals(0, info.getDeleted());
        assertEquals(0, info.getAdded());
    }

    @Test
    public void when4Added() {
        List<Analize.User> prev = new ArrayList<>();
        List<Analize.User> curr = List.of(
                new Analize.User(1, "Иванов Иван Иванович"),
                new Analize.User(2, "Петров Василий Иванович"),
                new Analize.User(3, "Баширов Валерий Викторович"),
                new Analize.User(4, "Потапов Михаил Евгеньевич")
        );
        Analize a = new Analize();
        Analize.Info info = a.diff(prev, curr);
        assertEquals(0, info.getChanged());
        assertEquals(0, info.getDeleted());
        assertEquals(4, info.getAdded());
    }

    @Test
    public void when4Deleted() {
        List<Analize.User> prev = List.of(
                new Analize.User(1, "Иванов Иван Иванович"),
                new Analize.User(2, "Петров Василий Иванович"),
                new Analize.User(3, "Баширов Валерий Викторович"),
                new Analize.User(4, "Потапов Михаил Евгеньевич")
        );
        List<Analize.User> curr = new ArrayList<>();
        Analize a = new Analize();
        Analize.Info info = a.diff(prev, curr);
        assertEquals(0, info.getChanged());
        assertEquals(4, info.getDeleted());
        assertEquals(0, info.getAdded());
    }

}