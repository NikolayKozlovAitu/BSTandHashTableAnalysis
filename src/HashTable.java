import java.util.LinkedList;

public class HashTable {

    private final int capacity;
    private final LinkedList<Integer>[] table;

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        this.capacity = capacity;
        this.table = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            this.table[i] = new LinkedList<>();
        }
    }

    private int hash(int key) {
        return Math.abs(Integer.hashCode(key)) % capacity;
    }

    public void insert(int key) {
        int index = hash(key);
        if (!table[index].contains(key)) {
            table[index].add(key);
        }
    }

    public boolean search(int key) {
        int index = hash(key);
        return table[index].contains(key);
    }

    public void delete(int key) {
        int index = hash(key);
        table[index].remove((Integer) key);
    }
}