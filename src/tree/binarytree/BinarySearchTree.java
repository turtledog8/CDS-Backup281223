package tree.binarytree;

import list.linkedlist.MyLinkedList;
import model.Station;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> root;

    public BinarySearchTree() {
        this.root = null;
    }

    // Insert a value into the BST
    public void insert(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }

        root = insertRec(root, value);
    }

    private Node<T> insertRec(Node<T> root, T value) {
        if (root == null) {
            return new Node<>(value);
        }

        int comparison = value.compareTo(root.data);

        if (comparison < 0) {
            root.left = insertRec(root.left, value);
        } else if (comparison > 0) {
            root.right = insertRec(root.right, value);
        } else {
            // Value is equal to the current node's data (duplicate)
            throw new IllegalArgumentException("Duplicate value: " + value);
        }

        return root;
    }

    // Search for a value in the BST
    public boolean search(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }

        return searchRec(root, value);
    }

    private boolean searchRec(Node<T> root, T value) {
        if (root == null) {
            return false;
        }

        if (value.equals(root.data)) {
            return true;
        }

        if (value.compareTo(root.data) < 0) {
            return searchRec(root.left, value);
        } else {
            return searchRec(root.right, value);
        }
    }

    // In-order traversal of the BST
    public void inOrderTraversal() {
        inOrderTraversalRecursive(root, data -> System.out.print(data + " "));
    }

    // In-order traversal of the BST with a custom action for each node
    public void inOrderTraversalRecursive(NodeAction<T> action) {
        inOrderTraversalRecursive(root, action);
    }

    private void inOrderTraversalRecursive(Node<T> root, NodeAction<T> action) {
        if (root != null) {
            inOrderTraversalRecursive(root.left, action);
            action.perform(root.data);
            inOrderTraversalRecursive(root.right, action);
        }
    }

    // Delete a value from the BST
    public void delete(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }

        root = deleteRec(root, value);
    }

    private Node<T> deleteRec(Node<T> root, T value) {
        if (root == null) {
            // Value not found
            throw new IllegalArgumentException("Value not found in the tree");
        }

        int comparison = value.compareTo(root.data);

        if (comparison < 0) {
            root.left = deleteRec(root.left, value);
        } else if (comparison > 0) {
            root.right = deleteRec(root.right, value);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children
            root.data = minValue(root.right);

            // Delete the in-order successor
            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }

    private T minValue(Node<T> root) {
        T minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }



    public MyLinkedList<Station> searchBySubstring(String searchSubstring) {
        MyLinkedList<Station> result = new MyLinkedList<>();
        searchBySubstringRecursive(root, searchSubstring.toLowerCase(), result);
        return result;
    }

    private void searchBySubstringRecursive(Node<T> root, String searchSubstring, MyLinkedList<Station> result) {
        if (root != null) {
            searchBySubstringRecursive(root.left, searchSubstring, result);

            // Check if the station's name contains the specified substring
            if (((Station) root.data).getNameMedium().toLowerCase().contains(searchSubstring)) {
                result.add((Station) root.data);
            }

            searchBySubstringRecursive(root.right, searchSubstring, result);
        }
    }



    // Functional interface for the action to be performed on each node
    @FunctionalInterface
    public interface NodeAction<T> {
        void perform(T data);
    }

    // Private Node class
    private static class Node<T> {
        T data;
        Node<T> left, right;

        Node(T value) {
            this.data = value;
            this.left = this.right = null;
        }
    }
    /**
     * Checks if the BST is empty.
     *
     * @return true if the BST is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Gets the number of nodes in the BST.
     *
     * @return the number of nodes in the BST
     */
    public int size() {
        return size(root);
    }

    private int size(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }

    /**
     * Gets the height of the BST.
     *
     * @return the height of the BST
     */
    public int height() {
        return height(root);
    }

    private int height(Node<T> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Generates a string representation of the BST.
     *
     * @return a string representation of the BST
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        inOrderTraversalRecursive(root, data -> sb.append(data).append(" "));
        return sb.toString().trim();
    }
}
