package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LogFilter {

    public static List<String> filter(String fileName) {
        List<String> result;
        Pattern search = Pattern.compile("\\s404\\s(\\d+|-)$");
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            result =
                    in.lines()
                    .filter((l) -> {
                        Matcher m = search.matcher(l);
                        return m.find();
                    })
                    .collect(Collectors.toList());
        } catch (Throwable ex) {
            System.out.println("Ошибка чтения файла " + fileName + ": " + ex.getMessage());
            result = new ArrayList<>();
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        log.stream().forEach(System.out::println);
    }
}
