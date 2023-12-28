package test;

import Tree.binarytree.BinarySearchTree;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTest {

    @Test
    void insertAndSearch() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        assertTrue(tree.search(10));
        assertTrue(tree.search(5));
        assertTrue(tree.search(15));
        assertFalse(tree.search(7));
    }

    @Test
    void insertAndDelete() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        tree.delete(5);

        assertTrue(tree.search(10));
        assertFalse(tree.search(5));
        assertTrue(tree.search(15));
    }

    @Test
    void insertNull() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        assertThrows(IllegalArgumentException.class, () -> tree.insert(null));
    }

    @Test
    void searchNull() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        assertThrows(IllegalArgumentException.class, () -> tree.search(null));
    }

    @Test
    void deleteNull() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        assertThrows(IllegalArgumentException.class, () -> tree.delete(null));
    }

    @Test
    void deleteNonexistentValue() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        assertThrows(IllegalArgumentException.class, () -> tree.delete(7));
    }

    @Test
    void inOrderTraversal() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        assertEquals("5 10 15", getInOrderTraversal(tree));
    }

    private String getInOrderTraversal(BinarySearchTree<Integer> tree) {
        StringBuilder result = new StringBuilder();
        tree.inOrderTraversalRecursive(data -> {
            result.append(data).append(" ");
        });
        return result.toString().trim();
    }

    @Test
    void insertAndDeleteEdgeCases() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        // Insert into an empty tree
        tree.insert(10);
        assertTrue(tree.search(10));

        // Insert and then delete the only node
        tree.delete(10);
        assertFalse(tree.search(10));
    }
    @Test
    void inOrderTraversalComplex() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(7);
        tree.insert(12);
        tree.insert(18);

        assertEquals("3 5 7 10 12 15 18", getInOrderTraversal(tree));
    }
    @Test
    void insertDuplicate() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(10);
        assertThrows(IllegalArgumentException.class, () -> tree.insert(10));
    }

    @Test
    void deleteNodeWithTwoChildren() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(7);
        tree.insert(12);
        tree.insert(18);

        tree.delete(15);

        assertFalse(tree.search(15));
        assertEquals("3 5 7 10 12 18", getInOrderTraversal(tree));
    }

    @Test
    void insertAndDeleteMultiple() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(7);
        tree.insert(12);
        tree.insert(18);

        tree.delete(5);
        tree.delete(15);
        tree.delete(7);

        assertFalse(tree.search(5));
        assertFalse(tree.search(15));
        assertFalse(tree.search(7));
        assertEquals("3 10 12 18", getInOrderTraversal(tree));
    }

    @Test
    void inOrderTraversalDynamic() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        // Insert a large number of elements
        for (int i = 1; i <= 10000; i++) {
            tree.insert(i);
        }

        // Search for elements
        for (int i = 1; i <= 10000; i++) {
            assertTrue(tree.search(i));
        }

        // Delete elements
        for (int i = 1; i <= 5000; i++) {
            tree.delete(i);
        }

        // Verify that deleted elements are no longer present
        for (int i = 1; i <= 5000; i++) {
            assertFalse(tree.search(i));
        }

        List<Integer> expectedOrder = IntStream.rangeClosed(1, 10000)
                .skip(5000) // Skip the first 5000 elements
                .boxed()
                .collect(Collectors.toList());

        // Verify the in-order traversal of the remaining elements
        List<Integer> actualOrder = new ArrayList<>();
        tree.inOrderTraversalRecursive(data -> actualOrder.add(data));

        assertEquals(expectedOrder, actualOrder);
    }

    @Test
    void inOrderTraversalAfterDeletion() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(7);
        tree.insert(12);
        tree.insert(18);

        tree.delete(5);

        assertEquals("3 7 10 12 15 18", getInOrderTraversal(tree));
    }

}
