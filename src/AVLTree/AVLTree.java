package AVLTree;

import java.util.*;

public class AVLTree<T extends Comparable<T>> {

    public void remove(T key) {
        root = remove(root, key);
    }

    public Node getRoot() {
        return root;
    }

    private Node remove(Node node, T key) {
        if (node == null) {
            return null;
        }

        // Perform standard BST delete
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
        } else {
            // Node with only one child or no child
            if (node.left == null || node.right == null) {
                Node temp = (node.left != null) ? node.left : node.right;

                // No child case
                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    // One child case
                    node = temp;
                }
            } else {
                // Node with two children: Get the inorder successor
                Node temp = findMin(node.right);

                // Copy the inorder successor's data to this node
                node.key = temp.key;

                // Remove only one occurrence of the key in the right subtree
                node.right = remove(node.right, temp.key);
            }
        }

        // If the tree had only one node then return
        if (node == null) {
            return null;
        }

        // Update height of the current node
        updateHeight(node);

        // Get the balance factor
        int balance = getBalance(node);

        // Perform rotations if necessary to balance the tree
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rotateRight(node);
        }

        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && getBalance(node.right) <= 0) {
            return rotateLeft(node);
        }

        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }


    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    private class Node {
        T key;
        int height;
        Node left, right;

        Node(T value) {
            key = value;
            height = 1;
        }
    }

    private Node root;

    // Get height of a node
    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // Update height of a node
    private void updateHeight(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }

    // Get balance factor of a node
    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    // Rotate right around the given node
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // Rotate left around the given node
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // Insert a key into the AVL tree
    public void insert(T key) {
        root = insert(root, key);
    }

    private Node insert(Node node, T key) {
        // Perform standard BST insert
        if (node == null) {
            return new Node(key);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = insert(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = insert(node.right, key);
        } else {
            // Duplicate keys are not allowed
            return node;
        }

        // Update height of the current node
        updateHeight(node);

        // Get the balance factor
        int balance = getBalance(node);

        // Perform rotations if necessary to balance the tree
        if (balance > 1 && key.compareTo(node.left.key) < 0) {
            return rotateRight(node);
        }

        if (balance < -1 && key.compareTo(node.right.key) > 0) {
            return rotateLeft(node);
        }

        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        // If the tree is already balanced, return the node
        return node;
    }

    // Perform an in-order traversal of the AVL tree
    public List<T> inOrderTraversal() {
        List<T> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result;
    }

    private void inOrderTraversal(Node node, List<T> result) {
        if (node != null) {
            inOrderTraversal(node.left, result);
            result.add(node.key);
            inOrderTraversal(node.right, result);
        }
    }
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }

        int balance = getBalance(node);
        return Math.abs(balance) <= 1 && isBalanced(node.left) && isBalanced(node.right);
    }
    public boolean isEmpty() {
        return root == null;
    }

    // Method to get the size of the tree
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }

    // Method to check if a key exists in the tree
    public boolean contains(T key) {
        return contains(root, key);
    }

    private boolean contains(Node node, T key) {
        if (node == null) {
            return false;
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            return contains(node.left, key);
        } else if (cmp > 0) {
            return contains(node.right, key);
        } else {
            return true; // Key found
        }
    }

    // Method to retrieve the value associated with a key
    public T get(T key) {
        return get(root, key);
    }

    private T get(Node node, T key) {
        if (node == null) {
            return null; // Key not found
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node.key; // Key found
        }
    }

    // Method to generate the Graphviz representation of the AVL tree
    public String graphViz() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph AVLTree {\n");
        graphViz(root, sb);
        sb.append("}\n");
        return sb.toString();
    }

    private void graphViz(Node node, StringBuilder sb) {
        if (node != null) {
            if (node.left != null) {
                sb.append("  \"").append(node.key).append("\" -> \"").append(node.left.key).append("\"\n");
                graphViz(node.left, sb);
            }
            if (node.right != null) {
                sb.append("  \"").append(node.key).append("\" -> \"").append(node.right.key).append("\"\n");
                graphViz(node.right, sb);
            }
        }
    }
}