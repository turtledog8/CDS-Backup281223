package map;

import list.linkedlist.MyLinkedList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * MyHashMap is a custom implementation of a HashMap.
 *
 * @param <K> the type of keys in the map
 * @param <V> the type of values in the map
 */
public class MyHashMap<K, V> implements CustomMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private List<MapNode<K, V>> buckets;
    private int size;

    /**
     * Constructs a new MyHashMap with default capacity.
     */
    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a new MyHashMap with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the hashmap
     */
    public MyHashMap(int initialCapacity) {
        this.buckets = new ArrayList<>(initialCapacity);
        for (int i = 0; i < initialCapacity; i++) {
            buckets.add(null);
        }
        this.size = 0;
    }

    /**
     * Associates the specified value with the specified key in this map.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    @Override
    public void put(K key, V value) {
        int index = getBucketIndex(key);
        MapNode<K, V> newNode = new MapNode<>(key, value);
        MapNode<K, V> current = buckets.get(index);

        if (current == null) {
            buckets.set(index, newNode);
        } else {
            while (current.getNext() != null) {
                if (current.getKey().equals(key)) {
                    current.setValue(value);
                    return;
                }
                current = current.getNext();
            }
            current.setNext(newNode);
        }

        size++;

        // Check if the load factor exceeds the threshold, resize the hashmap
        if ((double) size / buckets.size() > LOAD_FACTOR) {
            resize();
        }
    }

    /**
     * Returns the value to which the specified key is mapped.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped
     * @throws NullPointerException if the specified key is null
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new NullPointerException("Null keys are not allowed");
        }

        int index = getBucketIndex(key);
        MapNode<K, V> current = buckets.get(index);

        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
            current = current.getNext();
        }

        return null;
    }


    /**
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param key the key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key
     */
    @Override
    public boolean containsKey(K key) {
        int index = getBucketIndex(key);
        MapNode<K, V> current = buckets.get(index);

        while (current != null) {
            if (current.getKey().equals(key)) {
                return true;
            }
            current = current.getNext();
        }

        return false;
    }

    /**
     * Returns true if this map maps one or more keys to the specified value.
     *
     * @param value the value whose presence in this map is to be tested
     * @return true if this map maps one or more keys to the specified value
     */
    @Override
    public boolean containsValue(V value) {
        for (MapNode<K, V> bucket : buckets) {
            MapNode<K, V> current = bucket;
            while (current != null) {
                if (current.getValue().equals(value)) {
                    return true;
                }
                current = current.getNext();
            }
        }
        return false;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     *
     * @param key the key whose mapping is to be removed from the map
     */
    @Override
    public void remove(K key) {
        int index = getBucketIndex(key);
        MapNode<K, V> current = buckets.get(index);
        MapNode<K, V> prev = null;

        while (current != null) {
            if (current.getKey().equals(key)) {
                if (prev == null) {
                    buckets.set(index, current.getNext());
                } else {
                    prev.setNext(current.getNext());
                }
                size--;
                return;
            }
            prev = current;
            current = current.getNext();
        }
    }

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns true if this map contains no key-value mappings.
     *
     * @return true if this map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes all of the mappings from this map. The map will be empty after this call returns.
     */
    @Override
    public void clear() {
        buckets.clear();
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            buckets.add(null);
        }
        size = 0;
    }

    /**
     * Returns a set view of the keys contained in this map.
     *
     * @return a set view of the keys contained in this map
     */
    @Override
    public Iterable<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (MapNode<K, V> bucket : buckets) {
            MapNode<K, V> current = bucket;
            while (current != null) {
                keySet.add(current.getKey());
                current = current.getNext();
            }
        }
        return keySet;
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param key the key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key
     */
    @Override
    public boolean contains(K key) {
        int index = getBucketIndex(key);
        MapNode<K, V> current = buckets.get(index);

        while (current != null) {
            if (current.getKey().equals(key)) {
                return true;
            }
            current = current.getNext();
        }

        return false;
    }

    /**
     * Calculates the bucket index for a given key based on its hash code.
     *
     * @param key the key for which to calculate the bucket index
     * @return the bucket index for the key
     */
    private int getBucketIndex(K key) {
        if (key == null) {
            return 0; // or any other appropriate index for null keys
        }
        return Math.abs(key.hashCode()) % buckets.size();
    }

    /**
     * Resizes the hashmap when the load factor exceeds the threshold.
     * It rehashes all existing entries to new buckets.
     */
    private void resize() {
        List<MapNode<K, V>> oldBuckets = buckets;
        buckets = new ArrayList<>(2 * oldBuckets.size());

        for (int i = 0; i < 2 * oldBuckets.size(); i++) {
            buckets.add(null);
        }

        for (MapNode<K, V> oldBucket : oldBuckets) {
            MapNode<K, V> current = oldBucket;
            while (current != null) {
                put(current.getKey(), current.getValue());
                current = current.getNext();
            }
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or a default value if this map contains no mapping for the key.
     *
     * @param key           the key whose associated value is to be returned
     * @param defaultValue  the default value to be returned if the map contains no mapping for the key
     * @return the value to which the specified key is mapped, or the defaultValue if this map contains no mapping for the key
     */
    @Override
    public V getOrDefault(K key, V defaultValue) {
        int index = getBucketIndex(key);
        MapNode<K, V> current = buckets.get(index);

        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
            current = current.getNext();
        }

        return defaultValue;
    }

    /**
     * Searches for values in the hashmap whose key contains the specified substring.
     *
     * @param searchSubstring the substring to search for in the keys
     * @return a linked list containing values whose key contains the specified substring
     */

    public MyLinkedList<V> searchBySubstring(K searchSubstring) {
        MyLinkedList<V> matchingValues = new MyLinkedList<>();

        for (MapNode<K, V> bucket : buckets) {
            MapNode<K, V> current = bucket;
            while (current != null) {
                K currentKey = current.getKey();
                if (currentKey != null && currentKey.toString().contains(searchSubstring.toString())) {
                    matchingValues.add(current.getValue());
                }
                current = current.getNext();
            }
        }

        return matchingValues;
    }
    /**
     * Returns a string representation of the MyHashMap.
     *
     * @return a string representation of the MyHashMap
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");

        for (MapNode<K, V> bucket : buckets) {
            MapNode<K, V> current = bucket;
            while (current != null) {
                result.append(current.getKey()).append("=").append(current.getValue()).append(", ");
                current = current.getNext();
            }
        }

        // Remove the trailing comma and space if there are elements in the map
        if (result.length() > 1) {
            result.setLength(result.length() - 2);
        }

        result.append("}");
        return result.toString();
    }

}