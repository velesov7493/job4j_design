package ru.job4j.gc.cache;

import java.util.Scanner;

public class Emulator {

    private static Emulator instance;

    private DirFileCache cache;

    public Emulator() {
        cache = new DirFileCache("./data");
    }

    public void cmdChangeDir(String directoryName) {
        if (directoryName == null || directoryName.isEmpty()) {
            return;
        }
        cache = new DirFileCache(directoryName);
        System.out.println("Кешируемый каталог: " + directoryName);
    }

    public void cmdGetFile(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return;
        }
        String content = cache.get(fileName);
        System.out.println("Содержимое файла " + fileName + ":");
        System.out.println(content);
    }

    public void execute() {
        boolean finish = false;
        Scanner sc = new Scanner(System.in);
        while (!finish) {
            System.out.print("?>");
            String command = sc.nextLine();
            String[] words = command.split(" ");
            switch (words[0]) {
                case "cd": cmdChangeDir(words[1]); break;
                case "get": cmdGetFile(words[1]); break;
                case "exit": finish = true; break;
                default: System.out.println("Неизвестная команда: " + words[0]);
            }
        }
    }

    public static void main(String[] args) {
        instance = new Emulator();
        instance.execute();
    }
}
