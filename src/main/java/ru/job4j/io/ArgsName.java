package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values;

    public ArgsName() {
       values = new HashMap<>();
    }

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            String[] words = arg.split("=");
            if (
                    words.length != 2
                    || words[0].isEmpty()
                    || words[1].isEmpty()
                    || !words[0].startsWith("-")
            ) {
                throw new IllegalArgumentException();
            }
            values.put(words[0].substring(1), words[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
}
