package ru.job4j.generics3;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> mem = new HashMap<>();

    @Override
    public void add(T model) {
        mem.put(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = mem.containsKey(id);
        if (result) {

            mem.replace(id, model);
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = mem.containsKey(id);
        if (result) {
            mem.remove(id);
        }
        return result;
    }

    @Override
    public T findById(String id) {
        return mem.get(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MemStore<?> memStore = (MemStore<?>) o;
        return Objects.equals(mem, memStore.mem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mem);
    }
}
