package Map;

/**
 * CustomMap interface represents a simple key-value mapping.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public interface CustomMap<K, V> {

    /**
     * Associates the specified value with the specified key in this map.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    void put(K key, V value);

    /**
     * Returns the value to which the specified key is mapped, or null if this map
     * contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if no mapping for the key is found
     */
    V get(K key);

    /**
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param key the key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key, otherwise false
     */
    boolean containsKey(K key);

    /**
     * Returns true if this map maps one or more keys to the specified value.
     *
     * @param value the value whose presence in this map is to be tested
     * @return true if this map maps one or more keys to the specified value, otherwise false
     */
    boolean containsValue(V value);

    /**
     * Removes the mapping for the specified key from this map if present.
     *
     * @param key the key whose mapping is to be removed from the map
     */
    void remove(K key);

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map
     */
    int size();

    /**
     * Returns true if this map contains no key-value mappings.
     *
     * @return true if this map contains no key-value mappings, otherwise false
     */
    boolean isEmpty();

    /**
     * Removes all of the mappings from this map.
     */
    void clear();

    /**
     * Returns an iterable containing all the keys in this map.
     *
     * @return an iterable containing all the keys in this map
     */
    Iterable<K> keySet();

    /**
     * Returns true if this map contains the specified key.
     *
     * @param key the key to check for presence in this map
     * @return true if this map contains the specified key, otherwise false
     */
    boolean contains(K key);

    V getOrDefault(K key, V defaultValue);
}
