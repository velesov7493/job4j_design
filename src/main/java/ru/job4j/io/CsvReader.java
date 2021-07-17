package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class CsvReader {

    private String inFileName;
    private String outFileName;
    private String delimiter;
    private Set<String> filterColumns;
    private List<String> columns;
    private List<List<String>> data;

    public CsvReader(String[] args) {
        ArgsName nArgs = ArgsName.of(args);
        if (!checkArgs(nArgs)) {
            System.exit(2);
        }
    }

    private void displayUsage(String msg) {
        System.out.println(msg);
        System.out.println("Синтаксис:");
        System.out.println(
                "java -jar csvReader.jar"
                + "-path=ВходнойФайл "
                + "-delimiter=РазделительЗначений "
                + "-out=stdout|ВыходнойФайл "
                + "-filter=ИмяКолонки1,ИмяКолонки2,..ИмяКолонкиN"
        );
    }

    private boolean checkArgs(ArgsName args) {
        if (args.size() != 4) {
            displayUsage("Неправильное количество параметров!");
            return false;
        }
        outFileName = args.get("out");
        if (!"stdout".equals(outFileName)) {
            Path checkPath = Path.of(outFileName);
            if (!Files.exists(checkPath.getParent()) || Files.isDirectory(checkPath)) {
                displayUsage("Неверный путь к выходному файлу!");
                return false;
            }
        }
        inFileName = args.get("path");
        Path checkPath = Path.of(inFileName);
        if (!Files.exists(checkPath) || Files.isDirectory(checkPath)) {
            displayUsage("Неверный путь к входному файлу!");
            return false;
        }
        delimiter = args.get("delimiter");
        if (delimiter == null) {
            displayUsage("Не указан разделитель значений в строке!");
            return false;
        }
        String filter = args.get("filter");
        filterColumns = filter != null ? Set.of(filter.split(",")) : null;
        return true;
    }

    private void readFile() {
        data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(inFileName)
        )) {
            Scanner sc = new Scanner(br.readLine()).useDelimiter(delimiter);
            columns = sc.tokens().collect(Collectors.toList());
            while (br.ready()) {
                List<String> line = new ArrayList<>();
                sc = new Scanner(br.readLine()).useDelimiter(delimiter);
                for (int i = 0; i < columns.size(); i++) {
                    if (!sc.hasNext()) {
                        break;
                    }
                    line.add(sc.next());
                }
                data.add(line);
            }
        } catch (IOException ex) {
            System.out.println("Ошибка ввода/вывода при чтении файла: " + ex);
        }
    }

    private void saveToFile(List<String> lines) {
        try (PrintWriter pw =
            new PrintWriter(
                new BufferedWriter(
                    new FileWriter(outFileName)
        ))) {
            lines.forEach(pw::println);
        } catch (IOException ex) {
            System.out.println("Ошибка ввода/вывода при записи файла: " + ex);
        }
    }

    private void writeResults() {
        List<String> buffer = new ArrayList<>();
        for (List<String> line : data) {
            String cout = "";
            for (int i = 0; i < columns.size(); i++) {
                String colName = columns.get(i);
                if (filterColumns == null || filterColumns.contains(colName)) {
                    cout += String.format("%s; ", line.get(i));
                }
            }
            buffer.add(cout);
        }
        if ("stdout".equals(outFileName)) {
            buffer.forEach(System.out::println);
        } else {
            saveToFile(buffer);
        }
    }

    public static void main(String[] args) {
        CsvReader reader = new CsvReader(args);
        reader.readFile();
        reader.writeResults();
    }
}