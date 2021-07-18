package ru.job4j.lsp.foodstore.stores;

import ru.job4j.lsp.foodstore.products.Food;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractStore implements FoodStore {

    private Map<Food, Integer> store;

    public AbstractStore() {
        store = new HashMap<>();
    }

    @Override
    public Map<Food, Integer> getProducts() {
        return new HashMap<>(store);
    }

    @Override
    public void add(Food product, int count) {
        store.merge(product, count, Integer::sum);
    }

    @Override
    public int remove(Food product) {
        int result = store.get(product);
        store.remove(product);
        return result;
    }

    @Override
    public boolean moveTo(Food product, FoodStore dest) {
        int count = remove(product);
        boolean result = count != 0;
        if (result) {
            dest.add(product, count);
        }
        return result;
    }
}
