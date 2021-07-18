package ru.job4j.lsp.foodstore;

import ru.job4j.lsp.foodstore.products.Food;
import ru.job4j.lsp.foodstore.stores.FoodStore;

import java.util.List;
import java.util.Map;

public class ControlQuality {

    private List<FoodStore> stores;

    public ControlQuality(List<FoodStore> aStores) {
        stores = aStores;
    }

    private void distribute(Food product, int count) {
        stores.stream()
        .filter((s) -> s.toHere(product))
        .findFirst().ifPresent((s) -> s.add(product, count));
    }

    private void redistribute(Food product, FoodStore source) {
        stores.stream()
        .filter((d) -> d.toHere(product))
        .findFirst().ifPresent((d) -> {
            if (d == source) {
                return;
            }
            source.moveTo(product, d);
        });
    }

    public void distributeAll(Map<Food, Integer> products) {
        products.forEach(this::distribute);
    }

    public void redistributeAll() {
        for (FoodStore src : stores) {
            Map<Food, Integer> products = src.getProducts();
            for (Food key : products.keySet()) {
                redistribute(key, src);
            }
        }
    }
}
