public class BinarySearchTree {

    private static class Node {
        int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
        }
    }

    private Node root;

    public void insert(int key) {
        root = insertRecursive(root, key);
    }

    private Node insertRecursive(Node current, int key) {
        if (current == null) {
            return new Node(key);
        }
        if (key < current.key) {
            current.left = insertRecursive(current.left, key);
        } else if (key > current.key) {
            current.right = insertRecursive(current.right, key);
        }
        return current;
    }

    public boolean search(int key) {
        return searchRecursive(root, key);
    }

    private boolean searchRecursive(Node current, int key) {
        if (current == null) {
            return false;
        }
        if (key == current.key) {
            return true;
        }
        return key < current.key
                ? searchRecursive(current.left, key)
                : searchRecursive(current.right, key);
    }

    public void delete(int key) {
        root = deleteRecursive(root, key);
    }

    private Node deleteRecursive(Node current, int key) {
        if (current == null) {
            return null;
        }

        if (key < current.key) {
            current.left = deleteRecursive(current.left, key);
        } else if (key > current.key) {
            current.right = deleteRecursive(current.right, key);
        } else {
            if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            }

            current.key = findMin(current.right);
            current.right = deleteRecursive(current.right, current.key);
        }
        return current;
    }

    private int findMin(Node node) {
        int min = node.key;
        while (node.left != null) {
            min = node.left.key;
            node = node.left;
        }
        return min;
    }
}