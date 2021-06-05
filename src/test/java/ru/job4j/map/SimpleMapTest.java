package ru.job4j.map;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutSuccess() {
        SMap<String, User> passports = new SimpleMap<>();
        User mary = new User(
                "Румянцева Мария Сергеевна",
                3,
                new GregorianCalendar(1991, Calendar.FEBRUARY, 4)
        );
        assertTrue(passports.put("2404 113029", mary));
    }

    @Test
    public void whenPutFail() {
        SMap<String, User> passports = new SimpleMap<>();
        User mary = new User(
                "Румянцева Мария Сергеевна",
                3,
                new GregorianCalendar(1991, Calendar.FEBRUARY, 4)
        );
        User alex = new User(
                "Власов Александр Сергеевич",
                0,
                new GregorianCalendar(1984, Calendar.OCTOBER, 15)
        );
        assertTrue(passports.put("2404 113029", mary));
        assertFalse(passports.put("2404 113029", alex));
    }

    @Test
    public void whenGetSuccess() {
        SMap<String, User> passports = new SimpleMap<>();
        User mary = new User(
                "Румянцева Мария Сергеевна",
                3,
                new GregorianCalendar(1991, Calendar.FEBRUARY, 4)
        );
        passports.put("2404 113029", mary);
        assertEquals(mary, passports.get("2404 113029"));
    }

    @Test
    public void whenGetFail() {
        SMap<String, User> passports = new SimpleMap<>();
        assertNull(passports.get("2404 113029"));
    }

    @Test
    public void whenRemoveSuccess() {
        SMap<String, User> passports = new SimpleMap<>();
        User mary = new User(
                "Румянцева Мария Сергеевна",
                3,
                new GregorianCalendar(1991, Calendar.FEBRUARY, 4)
        );
        User alex = new User(
                "Власов Александр Сергеевич",
                0,
                new GregorianCalendar(1984, Calendar.OCTOBER, 15)
        );
        passports.put("2404 113029", mary);
        passports.put("2404 113530", alex);
        assertTrue(passports.remove("2404 113530"));
    }

    @Test
    public void whenRemoveFail() {
        SMap<String, User> passports = new SimpleMap<>();
        User mary = new User(
                "Румянцева Мария Сергеевна",
                3,
                new GregorianCalendar(1991, Calendar.FEBRUARY, 4)
        );
        User alex = new User(
                "Власов Александр Сергеевич",
                0,
                new GregorianCalendar(1984, Calendar.OCTOBER, 15)
        );
        passports.put("2404 113029", mary);
        passports.put("2404 113530", alex);
        assertFalse(passports.remove("2404 113529"));
    }

    @Test
    public void whenKeysIteratorSuccess() {
        SMap<String, User> passports = new SimpleMap<>();
        User mary = new User(
                "Румянцева Мария Сергеевна",
                3,
                new GregorianCalendar(1991, Calendar.FEBRUARY, 4)
        );
        passports.put("2404 113029", mary);
        Iterator<String> it = passports.iterator();
        assertEquals("2404 113029", it.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenKeysIteratorNoElement() {
        SMap<String, User> passports = new SimpleMap<>();
        Iterator<String> it = passports.iterator();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenKeysIteratorConcurrentModification() {
        SMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        Iterator<Integer> it = map.iterator();
        map.put(2, "two");
        it.next();
    }

    @Test
    public void whenExpand() {
        SMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");
        map.put(7, "seven");
        map.put(8, "eight");
        map.put(9, "nine");
        map.put(10, "ten");
        map.put(11, "eleven");
        assertEquals("five", map.get(5));
        assertEquals("nine", map.get(9));
        assertEquals("eleven", map.get(11));
    }
}