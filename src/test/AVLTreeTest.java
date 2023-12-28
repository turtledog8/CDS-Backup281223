package test;

import AVLTree.AVLTree;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AVLTreeTest {

    @Test
    public void testInsertAndInOrderTraversal() {
        AVLTree<Integer> avlTree = new AVLTree<Integer>();
        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(30);

        List<Integer> expected = Arrays.asList(10, 20, 30);
        List<Integer> result = avlTree.inOrderTraversal();
        assertEquals(expected, result);
    }

    @Test
    public void testInsertDuplicateKey() {
        AVLTree<Integer> avlTree = new AVLTree<Integer>();
        avlTree.insert(10);
        avlTree.insert(10);

        List<Integer> expected = Arrays.asList(10);
        List<Integer> result = avlTree.inOrderTraversal();
        assertEquals(expected, result);
    }

    @Test
    public void testInsertAndBalance() {
        AVLTree<Integer> avlTree = new AVLTree<Integer>();
        avlTree.insert(30);
        avlTree.insert(20);
        avlTree.insert(40);
        avlTree.insert(10);
        avlTree.insert(25);

        List<Integer> expected = Arrays.asList(10, 20, 25, 30, 40);
        List<Integer> result = avlTree.inOrderTraversal();
        assertEquals(expected, result);
    }

    @Test
    public void testInsertAndBalanceWithRotation() {
        AVLTree<Integer> avlTree = new AVLTree<Integer>();
        avlTree.insert(30);
        avlTree.insert(20);
        avlTree.insert(40);
        avlTree.insert(10);
        avlTree.insert(25);
        avlTree.insert(35);

        List<Integer> expected = Arrays.asList(10, 20, 25, 30, 35, 40);
        List<Integer> result = avlTree.inOrderTraversal();
        assertEquals(expected, result);
    }

    @Test
    public void testRemoveAndInOrderTraversal() {
        AVLTree<Integer> avlTree = new AVLTree<Integer>();
        avlTree.insert(30);
        avlTree.insert(20);
        avlTree.insert(40);
        avlTree.insert(10);
        avlTree.insert(25);
        avlTree.insert(35);

        avlTree.remove(25);

        List<Integer> expected = Arrays.asList(10, 20, 30, 35, 40);
        List<Integer> result = avlTree.inOrderTraversal();
        assertEquals(expected, result);
    }

    @Test
    public void testInsertAndInOrderTraversalString() {
        AVLTree<String> avlTree = new AVLTree<String>();
        avlTree.insert("apple");
        avlTree.insert("banana");
        avlTree.insert("orange");

        List<String> expected = Arrays.asList("apple", "banana", "orange");
        List<String> result = avlTree.inOrderTraversal();
        assertEquals(expected, result);
    }


    @Test
    public void testHeightBalance() {
        AVLTree<Integer> avlTree = new AVLTree<Integer>();
        avlTree.insert(30);
        avlTree.insert(20);
        avlTree.insert(40);
        avlTree.insert(10);
        avlTree.insert(25);
        avlTree.insert(35);

        assertTrue(avlTree.isBalanced());
    }


    @Test
    public void testEmptyTreeRemove() {
        AVLTree<Integer> avlTree = new AVLTree<Integer>();

        // Test behavior when removing a key from an empty tree.
        avlTree.remove(5); // No exception should be thrown, and the tree should remain empty.
        assertTrue(avlTree.inOrderTraversal().isEmpty());
    }

    @Test
    public void testRemoveNonExistentKey() {
        AVLTree<Integer> avlTree = new AVLTree<Integer>();

        // Test behavior when trying to remove a key that does not exist in the tree.
        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.remove(5); // No exception should be thrown, and the tree should remain unchanged.

        List<Integer> expected = Arrays.asList(10, 20);
        List<Integer> result = avlTree.inOrderTraversal();
        assertEquals(expected, result);
    }

    @Test
    public void testMultipleDuplicates() {
        AVLTree<Integer> avlTree = new AVLTree<Integer>();

        // Test inserting and removing nodes with multiple duplicates of the same key.
        avlTree.insert(10);
        System.out.println("After inserting 10: " + avlTree.inOrderTraversal());
        avlTree.insert(20);
        System.out.println("After inserting 20: " + avlTree.inOrderTraversal());
        avlTree.insert(30);
        System.out.println("After inserting 30: " + avlTree.inOrderTraversal());



        avlTree.remove(10);
        System.out.println("After removing 10: " + avlTree.inOrderTraversal());

        List<Integer> expected = Arrays.asList(20, 30); // Two occurrences of 10 should remain after removal.
        List<Integer> result = avlTree.inOrderTraversal();
        assertEquals(expected, result);
    }

    @Test
    public void testMixedTypes() {
        AVLTree<Double> avlTree = new AVLTree<Double>();

        // Test inserting keys of different types and ensure the tree handles them appropriately.
        avlTree.insert(10.0);
        avlTree.insert(15.5);
        avlTree.insert(20.0);

        List<Double> expected = Arrays.asList(10.0, 15.5, 20.0);
        List<Double> result = avlTree.inOrderTraversal();
        assertEquals(expected, result);
    }

    @Test
    public void testLargeDataset() {
        AVLTree<Integer> avlTree = new AVLTree<Integer>();

        // Test the performance and correctness with a larger dataset.
        IntStream.rangeClosed(1, 1000).forEach(avlTree::insert);

        List<Integer> expected = IntStream.rangeClosed(1, 1000).boxed().collect(Collectors.toList());
        List<Integer> result = avlTree.inOrderTraversal();
        assertEquals(expected, result);
    }

    @Test
    public void testRandomInsertionsAndDeletions() {
        AVLTree<Integer> avlTree = new AVLTree<Integer>();

        // Conduct tests where you insert and remove keys in a random order.
        // Implement test logic here.
    }

    @Test
    public void testEmptyTreeInOrderTraversal() {
        AVLTree<Integer> avlTree = new AVLTree<Integer>();

        // Test inOrderTraversal on an empty tree.
        List<Integer> result = avlTree.inOrderTraversal();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testEmptyTreeIsBalanced() {
        AVLTree<Integer> avlTree = new AVLTree<Integer>();

        // Test the behavior of isBalanced method on an empty tree.
        assertTrue(avlTree.isBalanced());
    }









}