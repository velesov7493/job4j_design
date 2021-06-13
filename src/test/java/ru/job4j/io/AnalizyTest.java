package ru.job4j.io;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Test
    public void whenManyPeriods() {
        List<String> expected = List.of(
                "10:57:01;10:59:01;",
                "11:01:02;11:02:02;"
        );
        assertThat(Analizy.unavailable(
                "./data/manyPeriods.log",
                "./data/manyPeriods.csv"),
                is(expected)
        );
    }

    @Test
    public void whenOnePeriod() {
        List<String> expected = List.of(
                "10:57:01;11:02:02;"
        );
        assertThat(Analizy.unavailable(
                "./data/onePeriod.log",
                "./data/onePeriod.csv"),
                is(expected)
        );
    }

}