package ru.job4j.gc.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache;

    public AbstractCache() {
        cache = new HashMap<>();
    }

    public V get(K key) {
        SoftReference<V> result = cache.get(key);
        if (result == null) {
            V value = load(key);
            if (value != null) {
                cache.put(key, new SoftReference<>(value));
            }
            return value;
        }
        return result.get();
    }

    protected abstract V load(K key);
}
