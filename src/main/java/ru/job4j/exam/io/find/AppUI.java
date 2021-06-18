package ru.job4j.exam.io.find;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class AppUI {

    private static final Logger LOG = LoggerFactory.getLogger(AppUI.class.getName());
    private static AppUI instance;

    private ArgsName namedArgs;
    private SearchVisitor visitor;
    private Pattern searchPt;

    public AppUI(String[] args) {
        try {
            namedArgs = ArgsName.of(args);
        } catch (IllegalArgumentException ex) {
            displayUsage("Неверные параметры!");
            System.exit(-1);
        }
    }

    private void displayUsage(String message) {
        System.out.println(message);
        System.out.println("Пример использования:");
        System.out.println("java -jar find.jar -d=dir -n=name -t=type -o=out");
        System.out.println("Где");
        System.out.println("\tdir - каталог начала поиска");
        System.out.println("\ttype - тип поиска");
        System.out.println("\t\t\"name\" - по имени файла");
        System.out.println(
                "\t\t\"mask\" - по маске файла "
                + "(например: *.avi или ?outer.php)"
        );
        System.out.println(
                "\t\t\"regex\" - по регулярному выражению "
                + "(например: .*\\.avi$ или ^\\w?outer\\.php$)"
        );
        System.out.println("\tname - имя файла или маска или регулярное выражение");
        System.out.println("\tout - путь к файлу вывода результатов");
    }

    private boolean checkArgs() {
        if (namedArgs.size() != 4) {
            displayUsage("Неправильное количество параметров!");
            return false;
        }
        File start;
        String searchType;
        String expression;
        Path out;
        try {
            start = new File(namedArgs.get("d"));
            searchType = namedArgs.get("t").toLowerCase();
            expression = namedArgs.get("n");
            out = Path.of(namedArgs.get("o"));
        } catch (IllegalArgumentException ex) {
            displayUsage("Неверные параметры!");
            return false;
        }
        Pattern ptSearchType = Pattern.compile("^(name|mask|regex)$");
        Pattern ptValidFileName = Pattern.compile("^[\\w-\\.]+$");
        Pattern ptValidMask = Pattern.compile("^[\\w-\\.\\*\\?]+$");
        Matcher m1 = ptSearchType.matcher(searchType);
        if (!(start.exists() && start.isDirectory())) {
            displayUsage("Стартовый каталог не существует или не является каталогом.");
            return false;
        }
        if (!m1.matches()) {
            displayUsage("Недопустимый тип поиска.");
            return false;
        }
        Matcher m2 = ptValidFileName.matcher(expression);
        Matcher m3 = ptValidMask.matcher(expression);
        String regex = "^.*$";
        if ("name".equals(searchType)) {
            if (!m2.matches()) {
                displayUsage("Недопустимое имя файла.");
                return false;
            }
            regex = expression.replace(".", "\\.");
            regex = "^" + regex + "$";
        } else if ("mask".equals(searchType)) {
            if (!m3.matches()) {
                displayUsage("Неправильный синтаксис маски поиска.");
                return false;
            }
            regex = expression.replace(".", "\\.");
            regex = regex.replace("*", ".*");
            regex = "^" + regex.replace("?", ".?") + "$";
        } else if ("regex".equals(searchType)) {
            regex = expression;
        }
        Matcher m4 = ptValidFileName.matcher(out.getFileName().toString());
        File outParent = out.getParent().toFile();
        if (!(m4.matches() && outParent.exists())) {
            displayUsage("Неправильный путь к файлу вывода результата.");
            return false;
        }
        try {
            searchPt = Pattern.compile(regex);
        } catch (PatternSyntaxException ex) {
            displayUsage("Неправильный синтаксис регулярного выражения поиска.");
            return false;
        }
        return true;
    }

    private void savePaths() {
        List<Path> pathList = visitor.getPaths();
        if (pathList.size() == 0) {
            return;
        }
        try (PrintWriter pw = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(namedArgs.get("o"))

        ))) {
            pathList.forEach(pw::println);
        } catch (IOException ex) {
            LOG.error("Ошибка записи результатов в файл.", ex);
        }
    }

    public void run() {
        if (!checkArgs()) {
            return;
        }
        visitor = new SearchVisitor((p) -> {
           String name = p.getFileName().toString();
           Matcher m = searchPt.matcher(name);
           return m.matches();
        });
        try {
            Files.walkFileTree(Path.of(namedArgs.get("d")), visitor);
        } catch (IOException ex) {
            LOG.error("Ошибка при обходе дерева каталогов.", ex);
        }
        savePaths();
    }

    public static void main(String[] args) {
        instance = new AppUI(args);
        instance.run();
    }
}
