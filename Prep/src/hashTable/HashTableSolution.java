package hashTable;

class MyHashTable<K, V> {
    private int SIZE = 31;
    
    private V[] array = (V[])new Object[SIZE];
    
    public void put(K key, V value) {
        
        if (key != null && value != null) {
            int index = hash(key.hashCode()) % SIZE;
            array[index] = value;
        }
    }
    
    public V get(K key) {
        return null;
    }
    
    private int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
}

public class HashTableSolution {

}
