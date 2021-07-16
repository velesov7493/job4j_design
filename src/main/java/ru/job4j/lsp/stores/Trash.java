package ru.job4j.lsp.stores;

import ru.job4j.lsp.products.Food;

public class Trash extends AbstractStore implements FoodStore {

    @Override
    public boolean toHere(Food product) {
        int ratio = product.getExpiredRatio();
        return ratio >= 100;
    }
}
