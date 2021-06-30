package ru.job4j.gc;

public class GcDemo {

    private static final long KB = 1024;
    private static final long MB = KB * KB;
    private static final long GB = MB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Состояние памяти ===");
        System.out.printf("Свободно: %d Мб%n", freeMemory / MB);
        System.out.printf("Всего: %d Мб%n", totalMemory / MB);
        System.out.printf("Максимум: %d Мб%n", maxMemory / MB);
    }

    public static void main(String[] args) {
        info();
        for (int i = 0; i < 10000; i++) {
            new Person(i, "N" + i);
        }
        System.gc();
        info();
    }
}
