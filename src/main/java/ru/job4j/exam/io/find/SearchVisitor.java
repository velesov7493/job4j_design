package ru.job4j.exam.io.find;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchVisitor extends SimpleFileVisitor<Path> {

    private Predicate<Path> condition;
    private List<Path> paths;

    public SearchVisitor(Predicate<Path> aCondition) {
        condition = aCondition;
        paths = new ArrayList<>();
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (condition.test(file)) {
            paths.add(file);
        }
        return super.visitFile(file, attrs);
    }

    public List<Path> getPaths() {
        return new ArrayList<>(paths);
    }
}
