package ru.job4j.isp.io;

public class ConsoleOutput implements IOutput {

    @Override
    public void println(Object obj) {
        System.out.println(obj);
    }
}
