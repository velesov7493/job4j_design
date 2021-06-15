package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) {
        String startPath = ".";
        String extension = "html";
        if (args.length == 2) {
            File checkFile = new File(args[0]);
            if (checkFile.exists() && checkFile.isDirectory()) {
                startPath = args[0];
            } else {
                System.out.println("Директория " + checkFile.getName() + " не существует!");
                System.out.println("Запуск с параметром по умолчанию - .");
            }
            extension = args[1];
        } else {
            System.out.println("Неверные параметры запуска!");
            System.out.println(
                    "Синтаксис: java -jar search.jar НачальныйКаталогПоиска РасширениеФайла"
            );
            System.out.println("Запуск с параметром по умолчанию - .");
        }
        final String ext = extension;
        Path start = Paths.get(startPath);
        search(start, p -> p.toFile().getName().endsWith("." + ext)).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) {
        SearchFiles searcher = new SearchFiles(condition);
        try {
            Files.walkFileTree(root, searcher);
        } catch (IOException ex) {
            System.out.println("Ошибка ввода/вывода при поиске фалов: " + ex.getMessage());
        }
        return searcher.getPaths();
    }
}
