package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserUsage {

    public static void main(String[] args) {

        User petya1 = new User(
                "Иванов Петр Михайлович",
                2, new GregorianCalendar(1960, Calendar.MAY, 30)
        );
        User petya2 = new User(
                "Иванов Петр Михайлович",
                2, new GregorianCalendar(1960, Calendar.MAY, 30)
        );
        User vasya1 = new User(
                "Безбородов Василий Михайлович",
                2, new GregorianCalendar(1960, Calendar.MAY, 30)
        );
        User petya3 = new User(
                "Иванов Петр Михайлович",
                2, new GregorianCalendar(1960, Calendar.MAY, 30)
        );
        User vasya2 = new User(
                "Иванов Василий Михайлович",
                2, new GregorianCalendar(1991, Calendar.JUNE, 22)
        );
        Map<User, Object> map1 = new HashMap<>();
        map1.put(petya1, new Object());
        map1.put(petya2, new Object());
        map1.put(vasya1, new Object());
        map1.put(petya3, new Object());
        map1.put(vasya2, new Object());
        for (User key : map1.keySet()) {
            System.out.println("[" + key + "] :" + map1.get(key));
        }

    }
}
