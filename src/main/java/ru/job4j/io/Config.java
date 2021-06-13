package ru.job4j.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values;

    public Config(final String path) {
        this.path = path;
        values = new HashMap<>();
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                if (line.startsWith("#") || line.isEmpty()) {
                    continue;
                }
                String[] expressions = line.split("=", 2);
                if (
                        expressions.length < 2
                        || expressions[0].isEmpty()
                        || expressions[1].isEmpty()
                ) {
                    throw new IllegalArgumentException();
                }
                values.put(expressions[0].trim(), expressions[1].trim());
            }
        } catch (IOException ex) {
            System.out.println("Ошибка чтения файла конфигурации: " + ex.getMessage());
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("../../app.properties"));
    }
}
