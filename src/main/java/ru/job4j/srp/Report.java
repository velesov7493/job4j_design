package ru.job4j.srp;

import java.util.Comparator;
import java.util.Set;
import java.util.function.Predicate;

public interface Report {

    String generate(
            Predicate<Employee> filter, Comparator<Employee> comparator,
            String salaryFormat, Set<String> columns
    );
}
