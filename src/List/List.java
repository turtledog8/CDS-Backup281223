package List;

/**
 * Interface for a double-linked list.
 *
 * @param <E> the type of elements in the list
 */
public interface List<E> {

    /**
     * Adds an element to the list.
     *
     * @param element the element to be added
     */
    void add(E element);

    /**
     * Adds an element at a specific index in the list.
     *
     * @param index   the index at which the element is to be added
     * @param element the element to be added
     */
    void add(int index, E element);

    /**
     * Removes a specific element from the list.
     *
     * @param element the element to be removed
     */
    void remove(E element);

    /**
     * Removes the element at a specific index from the list.
     *
     * @param index the index at which the element is to be removed
     */
    void remove(int index);

    /**
     * Gets the element at a specific index in the list.
     *
     * @param index the index of the element to be retrieved
     * @return the element at the specified index
     */
    E get(int index);

    /**
     * Checks if the list contains a specific element.
     *
     * @param element the element to be checked for existence
     * @return true if the element is in the list, false otherwise
     */
    boolean contains(E element);

    /**
     * Gets the size of the list.
     *
     * @return the number of elements in the list
     */
    int size();

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Clears all elements from the list.
     */
    void clear();

    /**
     * Sets the element at a specific index in the list.
     *
     * @param index the index at which the element is to be set
     * @param temp  the element to set at the specified index
     */
    void set(int index, E temp);
}
