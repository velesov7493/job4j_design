package ru.job4j.serialization;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class ActorTest {

    @Test
    public void whenXmlConvert() {
        Actor a1 = new Actor();
        a1.setId(77L);
        a1.setAvatarId(1233L);
        a1.setLogin("director8125");
        a1.setName("Иванов Иван Иванович");
        Calendar c = new GregorianCalendar();
        c.set(1980, Calendar.JANUARY, 1);
        a1.setBirthDate(c.getTime());
        a1.setPass("123456");
        a1.setSex("М");
        a1.setPhone("+79205104013");
        a1.setEmail("director8125@school.ru");
        ActorState state1 = new ActorState();
        state1.setId(21L);
        state1.setActorId(77L);
        state1.setRoleId(1);
        state1.setProfessionId(2);
        state1.setName("Директор школы");
        ActorState state2 = new ActorState();
        state2.setId(22L);
        state2.setActorId(77L);
        state2.setRoleId(4);
        state2.setProfessionId(3);
        state2.setDisciplineId(11);
        state2.setName("Преподаватель физики");
        a1.setStats(new ActorState[] {state1, state2});
        String xml = a1.toXML();
        Actor a2 = Actor.fromXML(xml);
        assertEquals(a1, a2);

    }

}