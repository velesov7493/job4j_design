package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    private final String logFileName;
    private final String botAnswersFileName;
    private List<String> log;

    public ConsoleChat(String aLogFileName, String answersFileName) {
        logFileName = aLogFileName;
        botAnswersFileName = answersFileName;
        log = new ArrayList<>();
    }

    private List<String> readAnswers() {
        List<String> result = null;
        try (BufferedReader br = new BufferedReader(
                new FileReader(botAnswersFileName)
        )) {
            result = br.lines().collect(Collectors.toList());
        } catch (IOException ex) {
            System.out.println("Ошибка открытия файла: " + ex.getMessage());
        }
        return result;
    }

    private void saveLog() {
        try (PrintWriter pw = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(logFileName)
        ))) {
            log.forEach(pw::println);
        } catch (IOException ex) {
            System.out.println("Ошибка записи журнала чата: " + ex.getMessage());
        }
    }

    public void run() {
        boolean finish = false;
        boolean active = true;
        List<String> answers = readAnswers();
        Scanner sc = new Scanner(System.in);
        while (!finish) {
            System.out.print(">>");
            String line = sc.nextLine();
            active =
                    !active && line.equals(CONTINUE)
                    || active && !line.equals(STOP);
            finish = line.equals(OUT);
            log.add(">>" + line);
            if (active && !finish) {
                int i = (int) (answers.size() * Math.random());
                System.out.println("bot>>" + answers.get(i));
                log.add("bot>>" + answers.get(i));
            }
        }
        saveLog();
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/chat.log", "./data/answers.txt");
        cc.run();
    }
}
