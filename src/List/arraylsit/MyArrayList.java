package List.arraylsit;

import List.List;

import java.util.ArrayList;

/**
 * MyArrayList class implements the List interface using an internal ArrayList.
 *
 * @param <E> the type of elements in the list
 */
public class MyArrayList<E> implements List<E> {

    private ArrayList<E> internalList;

    /**
     * Constructs a new MyArrayList with an internal ArrayList.
     */
    public MyArrayList() {
        internalList = new ArrayList<>();
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element the element to be added
     */
    @Override
    public void add(E element) {
        internalList.add(element);
    }

    /**
     * Adds an element at the specified index in the list.
     *
     * @param index   the index at which the element is to be inserted
     * @param element the element to be added
     */
    @Override
    public void add(int index, E element) {
        internalList.add(index, element);
    }

    /**
     * Removes the first occurrence of the specified element from the list.
     *
     * @param element the element to be removed
     */
    @Override
    public void remove(E element) {
        internalList.remove(element);
    }

    /**
     * Removes the element at the specified index in the list.
     *
     * @param index the index of the element to be removed
     */
    @Override
    public void remove(int index) {
        internalList.remove(index);
    }

    /**
     * Returns the element at the specified index in the list.
     *
     * @param index the index of the element to be retrieved
     * @return the element at the specified index
     */
    @Override
    public E get(int index) {
        return internalList.get(index);
    }

    /**
     * Checks if the list contains the specified element.
     *
     * @param element the element to be checked for existence in the list
     * @return true if the list contains the specified element, false otherwise
     */
    @Override
    public boolean contains(E element) {
        return internalList.contains(element);
    }

    /**
     * Returns the size of the list.
     *
     * @return the number of elements in the list
     */
    @Override
    public int size() {
        return internalList.size();
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    /**
     * Removes all elements from the list.
     */
    @Override
    public void clear() {
        internalList.clear();
    }

    /**
     * Sets the element at a specific index in the list.
     *
     * @param index the index at which the element is to be set
     * @param temp  the element to set at the specified index
     */
    @Override
    public void set(int index, E temp) {
        internalList.set(index, temp);
    }
}
