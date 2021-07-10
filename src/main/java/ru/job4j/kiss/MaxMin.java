package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {

    private <T> T findMaxMin(boolean maxSearch, List<T> list, Comparator<T> comparator) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        T result = list.get(0);
        for (T value : list) {
            int c =
                    maxSearch
                    ? comparator.compare(result, value)
                    : comparator.compare(value, result);
            if (c < 0) {
                result = value;
            }
        }
        return result;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return findMaxMin(true, value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return findMaxMin(false, value, comparator);
    }
}
