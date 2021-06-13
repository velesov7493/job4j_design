package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                int value = Integer.parseInt(line);
                if (value % 2 == 0) {
                    System.out.println(line + " - четное");
                } else {
                    System.out.println(line + " - нечетное");
                }
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}
