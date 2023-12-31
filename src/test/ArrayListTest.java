package test;

import list.arrayLsit.MyArrayList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
//100%
public class ArrayListTest {

    private MyArrayList<String> customArrayList;

    @Before
    public void setUp() {
        customArrayList = new MyArrayList<>();
    }

    @Test
    public void testAddAndGet() {
        customArrayList.add("Element 1");
        customArrayList.add("Element 2");
        assertEquals("Element 1", customArrayList.get(0));
        assertEquals("Element 2", customArrayList.get(1));
    }

    @Test
    public void testAddAtSpecificIndex() {
        customArrayList.add("Element 1");
        customArrayList.add("Element 2");
        customArrayList.add(1, "Element 3");
        assertEquals("Element 1", customArrayList.get(0));
        assertEquals("Element 3", customArrayList.get(1));
        assertEquals("Element 2", customArrayList.get(2));
    }

    @Test
    public void testRemove() {
        customArrayList.add("Element 1");
        customArrayList.add("Element 2");
        customArrayList.remove("Element 1");
        assertEquals("Element 2", customArrayList.get(0));
        assertEquals(1, customArrayList.size());
    }

    @Test
    public void testRemoveAtIndex() {
        customArrayList.add("Element 1");
        customArrayList.add("Element 2");
        customArrayList.remove(0);
        assertEquals("Element 2", customArrayList.get(0));
        assertEquals(1, customArrayList.size());
    }

    @Test
    public void testContains() {
        customArrayList.add("Element 1");
        customArrayList.add("Element 2");
        assertTrue(customArrayList.contains("Element 1"));
        assertFalse(customArrayList.contains("Element 3"));
    }

    @Test
    public void testSize() {
        customArrayList.add("Element 1");
        customArrayList.add("Element 2");
        assertEquals(2, customArrayList.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(customArrayList.isEmpty());
        customArrayList.add("Element 1");
        assertFalse(customArrayList.isEmpty());
    }

    @Test
    public void testClear() {
        customArrayList.add("Element 1");
        customArrayList.add("Element 2");
        customArrayList.clear();
        assertTrue(customArrayList.isEmpty());
    }
}
