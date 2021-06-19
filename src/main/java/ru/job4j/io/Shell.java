package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

public class Shell {

    private Path currentDir;

    public void cd(String path) {
        boolean isSubPath = !path.contains(File.separator);
        Path p = isSubPath ? Path.of(currentDir + File.separator + path) : Path.of(path);
        if (!(Files.exists(p) && Files.isDirectory(p))) {
            System.out.println("Каталог не существует или не является каталогом.");
            return;
        }
        try {
            currentDir = p.toRealPath(LinkOption.NOFOLLOW_LINKS);
        } catch (IOException ex) {
            System.out.println("Ошибка ввода-вывода: " + ex.getMessage());
        }
    }

    public String pwd() {
        return currentDir.toAbsolutePath().toString();
    }
}
