package ru.job4j.gc;

import java.util.Random;

public class GcTypesDemo {

    public static void main(String[] args) {
        Random random = new Random();
        int length = 100;
        String[] data = new String[1000000];
        for (int i = 0; ; i = (i + 1) % data.length) {
            data[i] = String.valueOf((char) random.nextInt(255)).repeat(length);
        }
    }
}
