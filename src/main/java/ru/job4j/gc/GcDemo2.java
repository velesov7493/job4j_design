package ru.job4j.gc;

import java.util.Scanner;

public class GcDemo2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String c = "abc";

        for (int i = 0; i < 100000; i++) {
            new User(i, "UserName" + i);
        }
        boolean finish = false;
        while (!finish) {
            String cmd = sc.nextLine();
            finish = "exit".equals(cmd);
        }
    }
}
