package ru.job4j.lsp.foodstore;

import org.junit.Test;
import ru.job4j.lsp.foodstore.products.Bread;
import ru.job4j.lsp.foodstore.products.Butter;
import ru.job4j.lsp.foodstore.products.Food;
import ru.job4j.lsp.foodstore.stores.FoodStore;
import ru.job4j.lsp.foodstore.stores.Shop;
import ru.job4j.lsp.foodstore.stores.Trash;
import ru.job4j.lsp.foodstore.stores.Warehouse;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ControlQualityTest {

    @Test
    public void whenDistributeToWarehouse() {
        Calendar cl = Calendar.getInstance();
        Date created = cl.getTime();
        cl.add(Calendar.DAY_OF_MONTH, 6);
        Date expired = cl.getTime();

        Map<Food, Integer> products = new HashMap<>();
        Bread product1 = new Bread();
        product1.setCreateDate(created);
        product1.setExpiryDate(expired);
        Butter product2 = new Butter();
        product2.setCreateDate(created);
        product2.setExpiryDate(expired);
        products.put(product1, 1000);
        products.put(product2, 500);
        FoodStore warehouse = new Warehouse();
        ControlQuality cq = new ControlQuality(List.of(warehouse));
        cq.distributeAll(products);
        assertThat(warehouse.getProducts().keySet(), is(products.keySet()));
    }

    @Test
    public void whenDistributeToShopAsNew() {
        Calendar cl = Calendar.getInstance();
        cl.add(Calendar.DAY_OF_MONTH, -1);
        Date created = cl.getTime();
        cl.add(Calendar.DAY_OF_MONTH, 3);
        Date expired = cl.getTime();
        Map<Food, Integer> products = new HashMap<>();
        Bread product1 = new Bread();
        product1.setCreateDate(created);
        product1.setExpiryDate(expired);
        Butter product2 = new Butter();
        product2.setCreateDate(created);
        product2.setExpiryDate(expired);
        products.put(product1, 1200);
        products.put(product2, 600);
        FoodStore shop = new Shop();
        ControlQuality cq = new ControlQuality(List.of(shop));
        cq.distributeAll(products);
        assertThat(shop.getProducts().keySet(), is(products.keySet()));
    }

    @Test
    public void whenDistributeToShopAsOld() {
        Calendar cl = Calendar.getInstance();
        cl.add(Calendar.DAY_OF_MONTH, -5);
        Date created = cl.getTime();
        cl.add(Calendar.DAY_OF_MONTH, 6);
        Date expired = cl.getTime();
        Map<Food, Integer> products = new HashMap<>();
        Bread product1 = new Bread();
        product1.setCreateDate(created);
        product1.setExpiryDate(expired);
        Butter product2 = new Butter();
        product2.setCreateDate(created);
        product2.setExpiryDate(expired);
        products.put(product1, 1200);
        products.put(product2, 600);
        FoodStore shop = new Shop();
        ControlQuality cq = new ControlQuality(List.of(shop));
        cq.distributeAll(products);
        product1.setDiscount(25.0f);
        product2.setDiscount(25.0f);
        Set<Food> expected = Set.of(product1, product2);
        assertThat(shop.getProducts().keySet(), is(expected));
    }

    @Test
    public void whenDistributeToTrash() {
        Calendar cl = Calendar.getInstance();
        cl.add(Calendar.DAY_OF_MONTH, -5);
        Date created = cl.getTime();
        cl.add(Calendar.DAY_OF_MONTH, 4);
        Date expired = cl.getTime();
        Map<Food, Integer> products = new HashMap<>();
        Bread product1 = new Bread();
        product1.setCreateDate(created);
        product1.setExpiryDate(expired);
        Butter product2 = new Butter();
        product2.setCreateDate(created);
        product2.setExpiryDate(expired);
        products.put(product1, 12);
        products.put(product2, 6);
        FoodStore trash = new Trash();
        Set<Food> expected = Set.of(product1, product2);
        ControlQuality cq = new ControlQuality(List.of(trash));
        cq.distributeAll(products);
        assertThat(trash.getProducts().keySet(), is(expected));
    }
}