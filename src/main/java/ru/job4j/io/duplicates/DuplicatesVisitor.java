package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private HashSet<FileProperty> files;

    public DuplicatesVisitor() {
        super();
        files = new HashSet<>();
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty checkFile = new FileProperty(
                file.toFile().length(),
                file.getFileName().toString()
        );
        if (files.contains(checkFile)) {
            System.out.println(file.toAbsolutePath() + " : " + file.toFile().length());
        } else {
            files.add(checkFile);
        }
        return super.visitFile(file, attrs);
    }
}
