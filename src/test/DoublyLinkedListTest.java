package test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import List.doublelinkedlist.MyDoublyLinkedList;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

//100% coverage
public class DoublyLinkedListTest {

    private MyDoublyLinkedList<String> customDoublyLinkedList;

    @Before
    public void setUp() {
        customDoublyLinkedList = new MyDoublyLinkedList<>();
    }

    @Test
    public void testAddAndGet() {
        customDoublyLinkedList.add("Element 1");
        customDoublyLinkedList.add("Element 2");
        assertEquals("Element 1", customDoublyLinkedList.get(0));
        assertEquals("Element 2", customDoublyLinkedList.get(1));
    }

    @Test
    public void testAddAtSpecificIndex() {
        customDoublyLinkedList.add("Element 1");
        customDoublyLinkedList.add("Element 2");
        customDoublyLinkedList.add(1, "Element 3");
        assertEquals("Element 1", customDoublyLinkedList.get(0));
        assertEquals("Element 3", customDoublyLinkedList.get(1));
        assertEquals("Element 2", customDoublyLinkedList.get(2));
    }

    @Test
    public void testRemove() {
        customDoublyLinkedList.add("Element 1");
        customDoublyLinkedList.add("Element 2");
        customDoublyLinkedList.remove("Element 1");
        assertEquals("Element 2", customDoublyLinkedList.get(0));
        assertEquals(1, customDoublyLinkedList.size());
    }

    @Test
    public void testRemoveAtIndex() {
        customDoublyLinkedList.add("Element 1");
        customDoublyLinkedList.add("Element 2");
        customDoublyLinkedList.remove(0);
        assertEquals("Element 2", customDoublyLinkedList.get(0));
        assertEquals(1, customDoublyLinkedList.size());
    }

    @Test
    public void testContains() {
        customDoublyLinkedList.add("Element 1");
        customDoublyLinkedList.add("Element 2");
        assertTrue(customDoublyLinkedList.contains("Element 1"));
        assertFalse(customDoublyLinkedList.contains("Element 3"));
    }

    @Test
    public void testSize() {
        customDoublyLinkedList.add("Element 1");
        customDoublyLinkedList.add("Element 2");
        assertEquals(2, customDoublyLinkedList.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(customDoublyLinkedList.isEmpty());
        customDoublyLinkedList.add("Element 1");
        assertFalse(customDoublyLinkedList.isEmpty());
    }

    @Test
    public void testClear() {
        customDoublyLinkedList.add("Element 1");
        customDoublyLinkedList.add("Element 2");
        customDoublyLinkedList.clear();
        assertTrue(customDoublyLinkedList.isEmpty());
    }

    @Test
    public void testIterator() {
        customDoublyLinkedList.add("Element 1");
        customDoublyLinkedList.add("Element 2");

        Iterator<String> iterator = customDoublyLinkedList.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Element 1", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Element 2", iterator.next());
        assertFalse(iterator.hasNext());
    }


    @Test
    public void testRemoveFromEmptyList() {
        assertThrows(IndexOutOfBoundsException.class, () -> customDoublyLinkedList.remove(0));
    }

    @Test
    public void testGetOutOfBounds() {
        customDoublyLinkedList.add("Element 1");
        assertThrows(IndexOutOfBoundsException.class, () -> customDoublyLinkedList.get(1));
    }

    @Test
    public void testRemoveNonExistingElement() {
        customDoublyLinkedList.add("Element 1");
        assertDoesNotThrow(() -> customDoublyLinkedList.remove("NonExistingElement"));
    }

    @Test
    public void stressTest() {
        for (int i = 0; i < 100000; i++) {
            customDoublyLinkedList.add("Element " + i);
        }
        assertEquals(100000, customDoublyLinkedList.size());

    }

    @Test
    public void testAddAtBeginningAndEnd() {
        customDoublyLinkedList.add("Element 1");

        // Add at the beginning
        customDoublyLinkedList.add(0, "New Element");

        // Add at the end
        customDoublyLinkedList.add(customDoublyLinkedList.size(), "Element 2");

        assertEquals("New Element", customDoublyLinkedList.get(0));
        assertEquals("Element 1", customDoublyLinkedList.get(1));
        assertEquals("Element 2", customDoublyLinkedList.get(2));
    }

    @Test
    public void testConcurrentModification() {
        customDoublyLinkedList.add("Element 1");
        customDoublyLinkedList.add("Element 2");

        Iterator<String> iterator = customDoublyLinkedList.iterator();
        assertTrue(iterator.hasNext());

        // Concurrent modification by adding an element
        customDoublyLinkedList.add("New Element");

        // Verify that the iterator still works
        assertTrue(iterator.hasNext());
        assertEquals("Element 1", iterator.next());
    }

    @Test
    public void testRandomElementRemoval() {
        for (int i = 0; i < 1000; i++) {
            customDoublyLinkedList.add("Element " + i);
        }

        for (int i = 0; i < 500; i++) {
            customDoublyLinkedList.remove("Element " + i);
        }

        assertEquals(500, customDoublyLinkedList.size());
    }







}
