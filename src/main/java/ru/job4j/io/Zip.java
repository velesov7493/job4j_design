package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private static boolean checkArgs(ArgsName args) {
        File checkFile = new File(args.get("d"));
        boolean result =
                checkFile.exists()
                && checkFile.isDirectory()
                && !args.get("o").isEmpty()
                && !args.get("e").isEmpty();
        if (!result) {
            System.out.println("Неверные параметры запуска!");
            System.out.println(
                    "Синтаксис: java -jar zip.jar "
                    + "-d=КаталогУпаковки -e=РасширениеИсключаемыхФайлов -o=ИмяФайлаАрхива"
            );
        }
        return result;
    }

    public static void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip =
                     new ZipOutputStream(
                             new BufferedOutputStream(
                                     new FileOutputStream(target)
                             ))
        ) {
            for (Path src : sources) {
                zip.putNextEntry(new ZipEntry(src.toString()));
                try (BufferedInputStream out =
                             new BufferedInputStream(
                                     new FileInputStream(src.toFile())
                )) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка при создании архива: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArgsName argNames = ArgsName.of(args);
        if (!checkArgs(argNames)) {
            return;
        }
        final String ext = argNames.get("e");
        File target = new File(argNames.get("o"));
        List<Path> sources = Search.search(
                Path.of(argNames.get("d")),
                p -> !p.toFile().getName().endsWith("." + ext)
        );
        packFiles(sources, target);
    }
}