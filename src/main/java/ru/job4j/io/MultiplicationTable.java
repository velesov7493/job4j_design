package ru.job4j.io;

import java.io.FileOutputStream;

public class MultiplicationTable {

    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("./data/MultipleTable.txt")) {
            for (int i = 1; i < 10; i++) {
                String line = i + ":";
                for (int j = 1; j < 10; j++) {
                    int value = i * j;
                    line += "\t" + value;
                }
                line += System.lineSeparator();
                out.write(line.getBytes());
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}
