package ru.job4j.serialization;

import org.junit.Test;

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

}