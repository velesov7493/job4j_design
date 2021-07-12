package ru.job4j.srp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class JsonReportEngine implements Report {

    private Store store;

    public JsonReportEngine(Store aStore) {
        store = aStore;
    }

    @Override
    public String generate(
            Predicate<Employee> filter, Comparator<Employee> comparator,
            String salaryFormat, Set<String> columns
    ) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        List<Employee> list = store.findBy(filter);
        JSONArray result = new JSONArray();
        for (Employee employee : list) {
            JSONObject entry = new JSONObject();
            if (columns == null || columns.contains("Name")) {
                entry.put("name", employee.getName());
            }
            if (columns == null || columns.contains("Hired")) {
                entry.put("hired", f.format(employee.getHired().getTime()));
            }
            if (columns == null || columns.contains("Fired")) {
                entry.put("fired", f.format(employee.getFired().getTime()));
            }
            if (columns == null || columns.contains("Salary")) {
                entry.put("salary", employee.getSalary());
            }
            result.put(entry);
        }
        return result.toString();
    }
}
