package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {

    public static List<String> unavailable(String srcFileName, String targetFileName) {
        List<String> timeMarks = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(srcFileName))) {
            String line = in.readLine();
            String beginTime = null;
            while (line != null) {
                String[] words = line.split(" ");
                int status = Integer.parseInt(words[0]);
                if (status >= 400 && beginTime == null) {
                    beginTime = words[1];
                }
                if (status < 400 && beginTime != null) {
                    timeMarks.add(beginTime + ";" + words[1] + ";");
                    beginTime = null;
                }
                line = in.readLine();
            }
        } catch (IOException ex) {
            System.out.println("Ошибка чтения файла лога: " + ex.getMessage());
        }
        if (timeMarks.size() == 0) {
            return timeMarks;
        }
        try (PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(targetFileName))
        )) {
            timeMarks.stream().forEach(out::println);
        } catch (IOException ex) {
            System.out.println("Ошибка записи меток времени: " + ex.getMessage());
        }
        return timeMarks;
    }
}
