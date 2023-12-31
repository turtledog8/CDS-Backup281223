package test;

import tree.minheap.MinHeap;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class MinHeapTest {
//100%
    @Test
    public void testInsertAndPeek() {
        MinHeap<Integer> minHeap = new MinHeap<>();
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(8);

        assertEquals(3, minHeap.peek());
    }

    @Test
    public void testPop() {
        MinHeap<Integer> minHeap = new MinHeap<>();
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(8);

        assertEquals(3, minHeap.pop());
        assertEquals(5, minHeap.pop());
        assertEquals(8, minHeap.pop());
        assertTrue(minHeap.isEmpty());
    }

    @Test
    public void testCustomComparator() {
        Comparator<Integer> reverseComparator = Comparator.reverseOrder();
        MinHeap<Integer> minHeap = new MinHeap<>(reverseComparator);

        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(8);

        assertEquals(8, minHeap.pop());
        assertEquals(5, minHeap.pop());
        assertEquals(3, minHeap.pop());
        assertTrue(minHeap.isEmpty());
    }

    @Test
    public void testIsEmpty() {
        MinHeap<Integer> minHeap = new MinHeap<>();
        assertTrue(minHeap.isEmpty());

        minHeap.insert(5);
        assertFalse(minHeap.isEmpty());
    }

    @Test
    public void testSize() {
        MinHeap<Integer> minHeap = new MinHeap<>();
        assertEquals(0, minHeap.size());

        minHeap.insert(5);
        minHeap.insert(3);
        assertEquals(2, minHeap.size());

        minHeap.pop();
        assertEquals(1, minHeap.size());
    }

    @Test
    public void testResizeArray() {
        MinHeap<Integer> minHeap = new MinHeap<>();
        for (int i = 1; i <= 15; i++) {
            minHeap.insert(i);
        }

        assertEquals(15, minHeap.size());
        assertEquals(1, minHeap.peek());
    }

    @Test
    public void testPeekOnEmptyHeap() {
        MinHeap<Integer> minHeap = new MinHeap<>();
        assertThrows(IllegalStateException.class, minHeap::peek);
    }

    @Test
    public void testPopOnEmptyHeap() {
        MinHeap<Integer> minHeap = new MinHeap<>();
        assertThrows(IllegalStateException.class, minHeap::pop);
    }

    @Test
    void testEmptyHeap() {
        MinHeap<Integer> emptyHeap = new MinHeap<>();
        assertTrue(emptyHeap.isEmpty());
        assertEquals(0, emptyHeap.size());

        assertThrows(IllegalStateException.class, () -> emptyHeap.pop());
        assertThrows(IllegalStateException.class, () -> emptyHeap.peek());
    }

    @Test
    void testSingleElementHeap() {
        MinHeap<Integer> singleElementHeap = new MinHeap<>();
        singleElementHeap.insert(42);

        assertFalse(singleElementHeap.isEmpty());
        assertEquals(1, singleElementHeap.size());
        assertEquals(42, singleElementHeap.peek());

        assertEquals(42, singleElementHeap.pop());
        assertTrue(singleElementHeap.isEmpty());

        assertThrows(IllegalStateException.class, () -> singleElementHeap.pop());
        assertThrows(IllegalStateException.class, () -> singleElementHeap.peek());
    }

    @Test
    void testLargeHeap() {
        MinHeap<Integer> largeHeap = new MinHeap<>();
        int n = 10000;

        for (int i = n; i >= 1; i--) {
            largeHeap.insert(i);
        }

        assertFalse(largeHeap.isEmpty());
        assertEquals(n, largeHeap.size());
        assertEquals(1, largeHeap.peek());

        for (int i = 1; i <= n; i++) {
            assertEquals(i, largeHeap.pop());
        }

        assertTrue(largeHeap.isEmpty());
    }

    @Test
    void testWithCustomComparator() {
        Comparator<Integer> reverseComparator = Comparator.reverseOrder();
        MinHeap<Integer> customComparatorHeap = new MinHeap<>(reverseComparator);

        customComparatorHeap.insert(5);
        customComparatorHeap.insert(3);
        customComparatorHeap.insert(8);

        assertEquals(8, customComparatorHeap.pop());
        assertEquals(5, customComparatorHeap.pop());
        assertEquals(3, customComparatorHeap.pop());
    }
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    void testInsertNullElementThrowsException() {
        MinHeap<Integer> nullHeap = new MinHeap<>();
        nullHeap.insert(3);

        // Ensure that IllegalArgumentException is thrown when inserting null
        assertThrows(IllegalArgumentException.class, () -> nullHeap.insert(null));
    }

    @Test
    void testWithDuplicates() {
        MinHeap<Integer> duplicateHeap = new MinHeap<>();
        duplicateHeap.insert(5);
        duplicateHeap.insert(3);
        duplicateHeap.insert(5);

        assertEquals(3, duplicateHeap.pop());
        assertEquals(5, duplicateHeap.pop());
        assertEquals(5, duplicateHeap.pop());
        assertTrue(duplicateHeap.isEmpty());
    }


    @Test
    void testNegativeNumbersAndZero() {
        MinHeap<Integer> negativeHeap = new MinHeap<>();
        negativeHeap.insert(-5);
        negativeHeap.insert(0);
        negativeHeap.insert(-3);

        assertEquals(-5, negativeHeap.pop());
        assertEquals(-3, negativeHeap.pop());
        assertEquals(0, negativeHeap.pop());
        assertTrue(negativeHeap.isEmpty());
    }

    @Test
    void testPerformance() {
        MinHeap<Integer> performanceHeap = new MinHeap<>();
        int numElements = 1000000;

        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= numElements; i++) {
            performanceHeap.insert(i);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Insertion time for " + numElements + " elements: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();

        for (int i = 1; i <= numElements; i++) {
            performanceHeap.pop();
        }

        endTime = System.currentTimeMillis();
        System.out.println("Popping time for " + numElements + " elements: " + (endTime - startTime) + " ms");

        assertTrue(performanceHeap.isEmpty());
    }

    @Test
    void testBoundary() {
        MinHeap<Integer> boundaryHeap = new MinHeap<>();
        int capacity = 1000000;

        for (int i = 1; i <= capacity; i++) {
            boundaryHeap.insert(i);
        }

        assertEquals(capacity, boundaryHeap.size());
        assertEquals(1, boundaryHeap.peek());

        for (int i = 1; i <= capacity; i++) {
            assertEquals(i, boundaryHeap.pop());
        }

        assertTrue(boundaryHeap.isEmpty());
    }

    @Test
    void testExceptionMessages() {
        MinHeap<Integer> exceptionHeap = new MinHeap<>();

        assertThrows(IllegalStateException.class, exceptionHeap::pop, "Heap is empty. Cannot pop from an empty heap.");
        assertThrows(IllegalStateException.class, exceptionHeap::peek, "Heap is empty. Cannot peek into an empty heap.");
    }

}
