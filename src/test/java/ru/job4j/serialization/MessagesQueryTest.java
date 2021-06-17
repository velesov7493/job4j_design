package ru.job4j.serialization;

import org.json.JSONObject;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class MessagesQueryTest {

    @Test
    public void whenJsonConvert() {
        String json =
                "{"
                + "\"chatId\":110,"
                + "\"actorId\":77,"
                + "\"dateInterval\":[\"2020-09-01\",\"2020-09-30\"],"
                + "\"reverseOrder\":0,"
                + "\"searchText\":\"история\","
                + "\"page\":1"
                + "}";
        MessagesQuery query = MessagesQuery.fromJson(json);
        assertEquals(json, query.toJson());
    }

    @Test
    public void whenJsonObjectDirectConvert() {
        String json =
                "{"
                + "\"chatId\":110,"
                + "\"actorId\":77,"
                + "\"dateInterval\":[\"2020-09-01\",\"2020-09-30\"],"
                + "\"reverseOrder\":0,"
                + "\"searchText\":\"история\","
                + "\"page\":1"
                + "}";
        JSONObject obj = new JSONObject(json);
        MessagesQuery result = MessagesQuery.fromJsonObject(obj);
        MessagesQuery expected = new MessagesQuery();
        expected.setChatId(110L);
        expected.setActorId(77L);
        Calendar c1 = new GregorianCalendar();
        c1.set(2020, Calendar.SEPTEMBER, 1, 0, 0, 0);
        c1.setTimeInMillis(c1.getTimeInMillis() - c1.getTimeInMillis() % 1000);
        Calendar c2 = new GregorianCalendar();
        c2.set(2020, Calendar.SEPTEMBER, 30, 0, 0, 0);
        c2.setTimeInMillis(c2.getTimeInMillis() - c2.getTimeInMillis() % 1000);
        expected.setDateInterval(new Date[]{c1.getTime(), c2.getTime()});
        expected.setReverseOrder((byte) 0);
        expected.setSearchText("история");
        expected.setPage(1);
        assertEquals(expected, result);
    }

    @Test
    public void whenJsonObjectConvert() {
        String json =
                "{"
                + "\"chatId\":110,"
                + "\"actorId\":77,"
                + "\"dateInterval\":[\"2020-09-01\",\"2020-09-30\"],"
                + "\"reverseOrder\":0,"
                + "\"searchText\":\"история\","
                + "\"page\":1"
                + "}";
        JSONObject obj = new JSONObject(json);
        MessagesQuery result = MessagesQuery.fromJsonObject(obj);
        MessagesQuery expected = new MessagesQuery();
        expected.setChatId(110L);
        expected.setActorId(77L);
        Calendar c1 = new GregorianCalendar();
        c1.set(2020, Calendar.SEPTEMBER, 1, 0, 0, 0);
        c1.setTimeInMillis(c1.getTimeInMillis() - c1.getTimeInMillis() % 1000);
        Calendar c2 = new GregorianCalendar();
        c2.set(2020, Calendar.SEPTEMBER, 30, 0, 0, 0);
        c2.setTimeInMillis(c2.getTimeInMillis() - c2.getTimeInMillis() % 1000);
        expected.setDateInterval(new Date[]{c1.getTime(), c2.getTime()});
        expected.setReverseOrder((byte) 0);
        expected.setSearchText("история");
        expected.setPage(1);
        assertEquals(expected, result);
    }

}