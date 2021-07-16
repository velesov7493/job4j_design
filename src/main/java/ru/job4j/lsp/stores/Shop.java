package ru.job4j.lsp.stores;

import ru.job4j.lsp.products.Food;

public class Shop extends AbstractStore implements FoodStore {

    @Override
    public boolean toHere(Food product) {
        int ratio = product.getExpiredRatio();
        return ratio >= 25 && ratio < 100;
    }

    @Override
    public void add(Food product, int count) {
        if (product.getExpiredRatio() > 75) {
            product.setDiscount(25.0f);
        }
        super.add(product, count);
    }
}
