package ru.job4j.io;

import java.io.File;

public class Dir {

    private static long directorySize(File directory) {
        long result = 0L;
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                result += file.isDirectory() ? directorySize(file) : file.length();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(
                    String.format("Файл не существует: %s", file.getAbsoluteFile())
            );
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("Это не директория: %s", file.getAbsoluteFile())
            );
        }
        System.out.println(String.format("Общий размер каталога: %s", directorySize(file)));
        File[] files = file.listFiles();
        if (files != null) {
            for (File subfile : files) {
                System.out.println(String.format(
                        "%-29s|%10d|%10d", subfile.getAbsoluteFile(),
                        subfile.length(), directorySize(subfile)
                ));
            }
        }
    }
}
