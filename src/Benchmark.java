import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Benchmark {

    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000};

        System.out.printf("%-8s | %-12s | %-13s | %-13s | %-13s%n",
                "Size", "Structure", "Insert (ms)", "Search (ms)", "Delete (ms)");
        System.out.println("-".repeat(70));

        for (int size : sizes) {
            List<Integer> data = generateRandomData(size);

            BinarySearchTree bst = new BinarySearchTree();

            long start = System.nanoTime();
            for (int key : data) {
                bst.insert(key);
            }
            double bstInsertMs = (System.nanoTime() - start) / 1e6;

            start = System.nanoTime();
            for (int key : data) {
                bst.search(key);
            }
            double bstSearchMs = (System.nanoTime() - start) / 1e6;

            start = System.nanoTime();
            for (int key : data) {
                bst.delete(key);
            }
            double bstDeleteMs = (System.nanoTime() - start) / 1e6;

            HashTable ht = new HashTable(Math.max(1007, size * 2));

            start = System.nanoTime();
            for (int key : data) {
                ht.insert(key);
            }
            double htInsertMs = (System.nanoTime() - start) / 1e6;

            start = System.nanoTime();
            for (int key : data) {
                ht.search(key);
            }
            double htSearchMs = (System.nanoTime() - start) / 1e6;

            start = System.nanoTime();
            for (int key : data) {
                ht.delete(key);
            }
            double htDeleteMs = (System.nanoTime() - start) / 1e6;

            System.out.printf("%-8d | %-12s | %-13.3f | %-13.3f | %-13.3f%n",
                    size, "BST", bstInsertMs, bstSearchMs, bstDeleteMs);
            System.out.printf("%-8d | %-12s | %-13.3f | %-13.3f | %-13.3f%n",
                    size, "Hash Table", htInsertMs, htSearchMs, htDeleteMs);
            System.out.println("-".repeat(70));
        }
    }

    private static List<Integer> generateRandomData(int size) {
        List<Integer> list = new ArrayList<>(size);
        Random random = new Random(42);
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(size * 100));
        }
        Collections.shuffle(list, random);
        return list;
    }
}