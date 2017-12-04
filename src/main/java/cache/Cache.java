package cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Cache {

    ConcurrentMap<String, List<String>> cache;

    public Cache() {
        cache = new ConcurrentHashMap<>();
    }

    public void cache(String key, List<String> value) {
        cache.putIfAbsent(key, value);
    }

    public ConcurrentMap<String, List<String>> getCache() {
        return cache;
    }

    public List<List<String>> asList() {
        return new ArrayList<>(cache.values());
    }
}
