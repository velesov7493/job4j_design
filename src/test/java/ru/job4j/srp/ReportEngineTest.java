package ru.job4j.srp;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.LinkedHashSet;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new TextReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(f.format(worker.getHired().getTime())).append(";")
                .append(f.format(worker.getFired().getTime())).append(";")
                .append(String.format("%10f", worker.getSalary())).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(
                em -> true, Comparator.comparing(Employee::getName),
                "%10f", null),
                is(expect.toString())
        );
    }

    @Test
    public void whenAccountingDepGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
        Employee worker = new Employee("Ivan", now, now, 13250.50);
        store.add(worker);
        Report engine = new TextReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(f.format(worker.getHired().getTime())).append(";")
                .append(f.format(worker.getFired().getTime())).append(";")
                .append(String.format("%10.2f", worker.getSalary())).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(
                em -> true, Comparator.comparing(Employee::getName),
                "%10.2f", null),
                is(expect.toString())
        );
    }

    @Test
    public void whenItDepGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
        Employee worker = new Employee("Ivan", now, now, 1325.50);
        store.add(worker);
        Report engine = new HtmlReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append("<html lang=\"ru\">")
                .append("<head>")
                .append("<meta charset=\"utf-8\"/>")
                .append("</head>")
                .append("<body>")
                .append("<table>")
                .append("<tr>")
                .append("<th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th>")
                .append("</tr>")
                .append("<tr>")
                .append("<td>")
                .append(worker.getName())
                .append("</td>")
                .append("<td>")
                .append(f.format(worker.getHired().getTime()))
                .append("</td>")
                .append("<td>")
                .append(f.format(worker.getFired().getTime()))
                .append("</td>")
                .append("<td>")
                .append(String.format("%10f", worker.getSalary()))
                .append("</td>")
                .append("</tr>")
                .append("</table>")
                .append("</body>")
                .append("</html>");
        assertThat(engine.generate(
                em -> true, Comparator.comparing(Employee::getName),
                "%10f", null),
                is(expect.toString())
        );
    }

    @Test
    public void whenHrDepGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 13250.50);
        Employee worker2 = new Employee("Alexandr", now, now, 23050);
        store.add(worker1);
        store.add(worker2);
        Report engine = new TextReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary; ")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(String.format("%10f", worker2.getSalary())).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(String.format("%10f", worker1.getSalary())).append(";")
                .append(System.lineSeparator());
        LinkedHashSet<String> columns = new LinkedHashSet<>();
        columns.add("Name");
        columns.add("Salary");
        assertThat(engine.generate(
                em -> true, (l, r) -> Double.compare(r.getSalary(), l.getSalary()),
                "%10f", columns),
                is(expect.toString())
        );
    }
}