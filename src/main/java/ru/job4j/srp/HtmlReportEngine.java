package ru.job4j.srp;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class HtmlReportEngine implements Report {

    private Store store;

    public HtmlReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(
            Predicate<Employee> filter, Comparator<Employee> comparator,
            String salaryFormat, Set<String> columns
    ) {
        SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>")
        .append("<html lang=\"ru\">")
        .append("<head>")
        .append("<meta charset=\"utf-8\"/>")
        .append("</head>")
        .append("<body>")
        .append("<table>")
        .append("<tr>");

        if (columns == null) {
            html.append("<th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th>");
        } else {
            for (String column : columns) {
                html.append("<th>")
                .append(column)
                .append("</th>");
            }
        }
        html.append("</tr>");
        List<Employee> list = store.findBy(filter);
        list.sort(comparator);
        for (Employee employee : list) {
            html.append("<tr>");
            if (columns == null || columns.contains("Name")) {
                html.append("<td>")
                .append(employee.getName())
                .append("</td>");
            }
            if (columns == null || columns.contains("Hired")) {
                html.append("<td>")
                .append(f.format(employee.getHired().getTime()))
                .append("</td>");
            }
            if (columns == null || columns.contains("Fired")) {
                html.append("<td>")
                .append(f.format(employee.getFired().getTime()))
                .append("</td>");
            }
            if (columns == null || columns.contains("Salary")) {
                html.append("<td>")
                .append(String.format(salaryFormat, employee.getSalary()))
                .append("</td>");
            }
            html.append("</tr>");
        }
        html.append("</table>")
        .append("</body>")
        .append("</html>");
        return html.toString();
    }
}
