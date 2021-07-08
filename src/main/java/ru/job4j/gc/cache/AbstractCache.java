package ru.job4j.gc.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache;

    public AbstractCache() {
        cache = new HashMap<>();
    }

    private V updateReference(K key) {
        V result = load(key);
        if (result != null) {
            cache.put(key, new SoftReference<>(result));
        }
        return result;
    }

    public V get(K key) {
        V value = cache.getOrDefault(key, new SoftReference<>(null)).get();
        if (value == null) {
            value = updateReference(key);
        }
        return value;
    }

    protected abstract V load(K key);
}
