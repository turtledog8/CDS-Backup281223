package map;

/**
 * Node class for a generic map, representing a key-value pair.
 *
 * @param <K> the type of keys in the map
 * @param <V> the type of values in the map
 */
class MapNode<K, V> {

    private K key;
    private V value;
    private MapNode<K, V> next;

    /**
     * Constructs a MapNode with the specified key and value.
     *
     * @param key   the key of the key-value pair
     * @param value the value of the key-value pair
     */
    public MapNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    /**
     * Gets the key of the key-value pair.
     *
     * @return the key of the key-value pair
     */
    public K getKey() {
        return key;
    }

    /**
     * Gets the value of the key-value pair.
     *
     * @return the value of the key-value pair
     */
    public V getValue() {
        return value;
    }

    /**
     * Gets the next MapNode in the linked list.
     *
     * @return the next MapNode in the linked list
     */
    public MapNode<K, V> getNext() {
        return next;
    }

    /**
     * Sets the next MapNode in the linked list.
     *
     * @param next the next MapNode in the linked list
     */
    public void setNext(MapNode<K, V> next) {
        this.next = next;
    }

    /**
     * Sets the value of the key-value pair.
     *
     * @param value the new value to be set
     */
    public void setValue(V value) {
        this.value = value;
    }
}
