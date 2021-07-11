package ru.job4j.srp;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class TextReportEngine implements Report {

    private Store store;

    public TextReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(
            Predicate<Employee> filter, Comparator<Employee> comparator,
            String salaryFormat, Set<String> columns
    ) {
        SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder text = new StringBuilder();
        if (columns == null) {
            text.append("Name; Hired; Fired; Salary;");
        } else {
            for (String column : columns) {
                text.append(column).append("; ");
            }
        }
        text.append(System.lineSeparator());
        List<Employee> list = store.findBy(filter);
        list.sort(comparator);
        for (Employee employee : list) {
            if (columns == null || columns.contains("Name")) {
                text.append(employee.getName()).append(";");
            }
            if (columns == null || columns.contains("Hired")) {
                text.append(f.format(employee.getHired().getTime())).append(";");
            }
            if (columns == null || columns.contains("Fired")) {
                text.append(f.format(employee.getFired().getTime())).append(";");
            }
            if (columns == null || columns.contains("Salary")) {
                text.append(String.format(salaryFormat, employee.getSalary())).append(";");
            }
            text.append(System.lineSeparator());
        }
        return text.toString();
    }
}
