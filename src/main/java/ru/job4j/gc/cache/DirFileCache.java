package ru.job4j.gc.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringJoiner;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String aCachingDir) {
        super();
        cachingDir = aCachingDir;
    }

    @Override
    protected String load(String key) {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        String fullName = cachingDir + "/" + key;
        try (BufferedReader br = new BufferedReader(new FileReader(fullName))) {
            br.lines().forEach(sj::add);
        } catch (IOException ex) {
            System.out.println("Ошибка ввода/вывода при чтении файла " + fullName + ": " + ex);
            ex.printStackTrace();
        }
        return sj.toString();
    }
}
