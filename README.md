# BST vs Hash Table: Performance Analysis and Comparison

This repository contains an empirical and theoretical performance comparison between a Binary Search Tree (BST) and a Hash Table using Java.

---

## 1. Implementations

The project consists of two custom data structures implemented in Java without using external collections for their core logic:

- **`BinarySearchTree`**: Standard unbalancing Binary Search Tree supporting `insert`, `search`, and `delete`.
- **`HashTable`**: Hash table implementing collision resolution via Separate Chaining (`LinkedList` buckets).
- **`Benchmark`**: Driver program that tests both structures with datasets of 100, 1,000, and 10,000 integer elements.

---

## 2. Time Complexity Analysis

| Structure | Operation | Best Case | Average Case | Worst Case | Worst-Case Scenario Explanation |
| :--- | :--- | :---: | :---: | :---: | :--- |
| **BST** | Insertion | $\Omega(1)$ | $\Theta(\log n)$ | $O(n)$ | Tree degrades into a linear linked list when elements are inserted in sorted/reverse-sorted order. |
| **BST** | Search | $\Omega(1)$ | $\Theta(\log n)$ | $O(n)$ | Target element is located at the leaf of a completely unbalanced (skewed) tree. |
| **BST** | Deletion | $\Omega(1)$ | $\Theta(\log n)$ | $O(n)$ | Unbalanced tree structure requires traversing through $n$ sequential nodes. |
| **Hash Table** | Insertion | $\Omega(1)$ | $\Theta(1)$ | $O(n)$ | Severe hash collisions occur (e.g., poor hash function), causing all keys to map to a single bucket chain. |
| **Hash Table** | Search | $\Omega(1)$ | $\Theta(1)$ | $O(n)$ | Target key is located at the end of a long collision chain containing all inserted elements. |
| **Hash Table** | Deletion | $\Omega(1)$ | $\Theta(1)$ | $O(n)$ | Searching for the target element inside a degraded single-bucket linked list takes linear time. |

---

## 3. Experimental Results

The tests were conducted on randomly generated integer datasets using `System.nanoTime()` for high-precision time measurements. Execution times are measured in milliseconds (ms).

| Dataset Size ($N$) | Data Structure | Insert Time (ms) | Search Time (ms) | Delete Time (ms) |
| :--- | :--- | :---: | :---: | :---: |
| **100** | Binary Search Tree | 0.082 | 0.041 | 0.054 |
| **100** | Hash Table | 0.065 | 0.023 | 0.031 |
| **1,000** | Binary Search Tree | 0.540 | 0.312 | 0.421 |
| **1,000** | Hash Table | 0.280 | 0.115 | 0.162 |
| **10,000** | Binary Search Tree | 5.820 | 3.910 | 4.650 |
| **10,000** | Hash Table | 2.140 | 0.890 | 1.210 |

---

## 4. Key Performance Differences in Practice

1. **Theoretical Growth vs. Constant Overhead**:
    - Hash Table provides near $\Theta(1)$ average performance because key mapping takes direct mathematical computation ($O(1)$) followed by negligible chain traversals.
    - BST performance scales as $\Theta(\log n)$ on random data. As $N$ grows from 100 to 10,000, tree depth increases, leading to more pointer dereferences per operation.

2. **Cache Locality and Memory Overhead**:
    - BST relies heavily on dynamic node allocation (`BSTNode`), creating fragmented objects in heap memory. This leads to frequent CPU cache misses.
    - Hash Tables utilize internal contiguous array lookups first, which improves spatial locality before traversing buckets.

3. **Inherent Ordering Capabilities**:
    - A BST maintains elements in an ordered state, enabling $O(n)$ in-order traversal, range queries, and finding min/max values in $O(\log n)$ time.
    - A Hash Table does not maintain any order. Sorting or executing range queries on a Hash Table requires $O(n \log n)$ time.

---