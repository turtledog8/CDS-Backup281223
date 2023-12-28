package test;

import static org.junit.Assert.*;

import Map.MyHashMap;
import org.junit.Before;
import org.junit.Test;

public class HashMapTest {

    private MyHashMap<String, Integer> customHashMap;

    @Before
    public void setUp() {
        customHashMap = new MyHashMap<>();
    }

    @Test
    public void testPutAndGet() {
        customHashMap.put("One", 1);
        customHashMap.put("Two", 2);
        assertEquals(Integer.valueOf(1), customHashMap.get("One"));
        assertEquals(Integer.valueOf(2), customHashMap.get("Two"));
    }

    @Test
    public void testContainsKey() {
        customHashMap.put("One", 1);
        assertTrue(customHashMap.containsKey("One"));
        assertFalse(customHashMap.containsKey("Two"));
    }

    @Test
    public void testContainsValue() {
        customHashMap.put("One", 1);
        customHashMap.put("Two", 2);
        assertTrue(customHashMap.containsValue(1));
        assertFalse(customHashMap.containsValue(3));
    }

    @Test
    public void testRemove() {
        customHashMap.put("One", 1);
        customHashMap.remove("One");
        assertFalse(customHashMap.containsKey("One"));
        assertEquals(0, customHashMap.size());
    }

    @Test
    public void testSize() {
        customHashMap.put("One", 1);
        customHashMap.put("Two", 2);
        assertEquals(2, customHashMap.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(customHashMap.isEmpty());
        customHashMap.put("One", 1);
        assertFalse(customHashMap.isEmpty());
    }

    @Test
    public void testClear() {
        customHashMap.put("One", 1);
        customHashMap.put("Two", 2);
        customHashMap.clear();
        assertTrue(customHashMap.isEmpty());
        assertEquals(0, customHashMap.size());
    }

    @Test
    public void testCollisionHandling() {
        customHashMap.put("One", 1);
        customHashMap.put("Neo", 3); // Collision with "One" in the same bucket
        assertEquals(Integer.valueOf(1), customHashMap.get("One"));
        assertEquals(Integer.valueOf(3), customHashMap.get("Neo"));
    }

    @Test
    public void testNullKeyHandling() {
        customHashMap.put(null, 5);

        // Use assertThrows to test for the expected exception
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            customHashMap.get(null);
        });

        // Assert further if needed
        assertEquals("Null keys are not allowed", exception.getMessage());

    }

    @Test
    public void testGetOrDefault() {
        customHashMap.put("One", 1);
        assertEquals(Integer.valueOf(1), customHashMap.getOrDefault("One", 10));
        assertEquals(Integer.valueOf(10), customHashMap.getOrDefault("Two", 10));
    }

    @Test
    public void testEdgeCases() {
        for (int i = 0; i < 15; i++) {
            customHashMap.put("EdgeKey" + i, i);
        }
        assertTrue(customHashMap.containsKey("EdgeKey5"));
    }

    @Test
    public void testRemoveNonExistingKey() {
        customHashMap.put("One", 1);
        customHashMap.remove("Two");
        assertTrue(customHashMap.containsKey("One"));
    }

    @Test
    public void testGetNonExistingKey() {
        assertNull(customHashMap.get("NonExistingKey"));
    }
}

