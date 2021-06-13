package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenManyPeriods() throws IOException {
        StringBuilder result = new StringBuilder();
        String expected =
                "10:57:01;10:59:01;11:01:02;11:02:02;";
        File target = folder.newFile("manyPeriods.csv");
        Analizy.unavailable("./data/manyPeriods.log", target.getAbsolutePath());
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(result::append);
        }
        assertThat(result.toString(), is(expected));
    }

    @Test
    public void whenOnePeriod() throws IOException {
        StringBuilder result = new StringBuilder();
        File target = folder.newFile("onePeriod.csv");
        Analizy.unavailable("./data/onePeriod.log", target.getAbsolutePath());
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(result::append);
        }
        assertThat(result.toString(), is("10:57:01;11:02:02;"));
    }

}