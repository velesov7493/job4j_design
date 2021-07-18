package ru.job4j.lsp.foodstore.stores;

import ru.job4j.lsp.foodstore.products.Food;

import java.util.Map;

public interface FoodStore {

    Map<Food, Integer> getProducts();

    boolean toHere(Food product);

    void add(Food product, int count);

    int remove(Food product);

    boolean moveTo(Food product, FoodStore dest);
}
