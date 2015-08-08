package lru_cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class LRUCache<K, V> {
    
    private Map<K, V> map;
    private Queue<K> queue = new LinkedList<>();
    private int size;
    
    public LRUCache(int size) {
        this.size = size;
        map = new HashMap<>(size);
    }
    
    public V get(K key) {
        return map.get(key);
    }
    
    public void put(K key, V value) {
        
        if (map.containsKey(key)) {
            map.remove(key);
        }
        
        while(queue.size() >= size) {
            K oldest = queue.poll();
            
            if (oldest != null) {
                map.remove(oldest);
            }
        }
        
        queue.add(key);
        map.put(key, value);
    }
    
    public String toString() {
        return map.toString();
    }
}

public class LRUCacheSolution {

    public static void main(String[] args) {
        LRUCache<String, String> cache = new LRUCache<>(3);
        
        cache.put("A", "A");
        cache.put("B", "B");
        cache.put("C", "C");
        cache.put("D", "D");
        cache.put("E", "E");
        
        System.out.println(cache);
    }
}
