package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {
        String startPath = ".";
        if (args.length == 1) {
            File checkFile = new File(args[0]);
            if (checkFile.exists() && checkFile.isDirectory()) {
                startPath = args[0];
            } else {
                System.out.println("Директория " + checkFile.getName() + " не существует!");
                System.out.println("Запуск с параметром по умолчанию - .");
            }
        } else {
            System.out.println("Неверные параметры запуска!");
            System.out.println("Синтаксис: java -jar duplicatesFinder.jar НачальныйКаталогПоиска");
            System.out.println("Запуск с параметром по умолчанию - .");
        }
        Files.walkFileTree(Path.of(startPath), new DuplicatesVisitor());
    }
}
