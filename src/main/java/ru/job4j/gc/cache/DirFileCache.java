package ru.job4j.gc.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String aCachingDir) {
        super();
        cachingDir = aCachingDir;
    }

    @Override
    protected String load(String key) {
        String result = null;
        String fullName = cachingDir + "/" + key;
        try {
            result = Files.readString(Path.of(fullName));
        } catch (IOException ex) {
            System.out.println("Ошибка ввода/вывода при чтении файла " + fullName + ": " + ex);
            ex.printStackTrace();
        }
        return result;
    }
}
