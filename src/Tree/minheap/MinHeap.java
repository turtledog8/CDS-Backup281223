package Tree.minheap;

import java.util.Comparator;

/**
 * A generic MinHeap implementation that stores elements of type T.
 *
 * @param <T> The type of elements in the heap, must extend Comparable<T>.
 */
public class MinHeap<T extends Comparable<T>> {

    private final int DEFAULT_CAPACITY = 10;
    private Object[] arr;
    private int size;
    private final Comparator<T> comparator;

    /**
     * Constructs a MinHeap with the specified comparator.
     *
     * @param comparator The comparator to determine the order of elements. Null to use natural ordering.
     */
    public MinHeap(Comparator<T> comparator) {
        this.arr = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.comparator = comparator;
    }

    /**
     * Constructs a MinHeap using natural ordering.
     */
    public MinHeap() {
        this(null); // Use natural ordering if no comparator is provided
    }

    /**
     * Inserts an element into the heap.
     *
     * @param element The element to be inserted.
     */
    public void insert(T element) {
        push(element);
    }

    /**
     * Pushes an element onto the heap.
     *
     * @param element The element to be pushed.
     */
    public void push(T element) {
        if (size == arr.length) {
            resizeArray();
        }

        arr[size] = element;
        size++;
        heapifyUp();
    }

    /**
     * Removes and returns the minimum element from the heap.
     *
     * @return The minimum element in the heap.
     * @throws IllegalStateException If the heap is empty.
     */
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty. Cannot pop from an empty heap.");
        }

        T min = getElement(0);
        arr[0] = getElement(size - 1);
        size--;
        heapifyDown();
        return min;
    }

    /**
     * Returns the minimum element in the heap without removing it.
     *
     * @return The minimum element in the heap.
     * @throws IllegalStateException If the heap is empty.
     */
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty. Cannot peek into an empty heap.");
        }

        return getElement(0);
    }

    /**
     * Checks if the heap is empty.
     *
     * @return True if the heap is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the heap.
     *
     * @return The size of the heap.
     */
    public int size() {
        return size;
    }

    /**
     * Performs the heapify-up operation to maintain the heap property.
     */
    private void heapifyUp() {
        int currentIndex = size - 1;

        while (hasParent(currentIndex) && compare(getParent(currentIndex), getElement(currentIndex)) > 0) {
            swap(getParentIndex(currentIndex), currentIndex);
            currentIndex = getParentIndex(currentIndex);
        }
    }

    /**
     * Performs the heapify-down operation to maintain the heap property.
     */
    private void heapifyDown() {
        int currentIndex = 0;

        while (hasLeftChild(currentIndex)) {
            int smallerChildIndex = getLeftChildIndex(currentIndex);
            if (hasRightChild(currentIndex) && compare(getRightChild(currentIndex), getLeftChild(currentIndex)) < 0) {
                smallerChildIndex = getRightChildIndex(currentIndex);
            }

            if (compare(getElement(currentIndex), getElement(smallerChildIndex)) < 0) {
                break;
            } else {
                swap(currentIndex, smallerChildIndex);
            }

            currentIndex = smallerChildIndex;
        }
    }

    /**
     * Swaps elements at the given indices in the array.
     *
     * @param index1 The index of the first element.
     * @param index2 The index of the second element.
     */
    private void swap(int index1, int index2) {
        T temp = getElement(index1);
        arr[index1] = getElement(index2);
        arr[index2] = temp;
    }

    /**
     * Checks if an element has a parent in the heap.
     *
     * @param index The index of the element.
     * @return True if the element has a parent, false otherwise.
     */
    private boolean hasParent(int index) {
        return index > 0;
    }

    /**
     * Returns the index of the parent of an element.
     *
     * @param index The index of the element.
     * @return The index of the parent.
     */
    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    /**
     * Returns the parent of an element.
     *
     * @param index The index of the element.
     * @return The parent of the element.
     */
    private T getParent(int index) {
        return getElement(getParentIndex(index));
    }

    /**
     * Checks if an element has a left child in the heap.
     *
     * @param index The index of the element.
     * @return True if the element has a left child, false otherwise.
     */
    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    /**
     * Returns the index of the left child of an element.
     *
     * @param index The index of the element.
     * @return The index of the left child.
     */
    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    /**
     * Returns the left child of an element.
     *
     * @param index The index of the element.
     * @return The left child of the element.
     */
    private T getLeftChild(int index) {
        return getElement(getLeftChildIndex(index));
    }

    /**
     * Checks if an element has a right child in the heap.
     *
     * @param index The index of the element.
     * @return True if the element has a right child, false otherwise.
     */
    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    /**
     * Returns the index of the right child of an element.
     *
     * @param index The index of the element.
     * @return The index of the right child.
     */
    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    /**
     * Returns the right child of an element.
     *
     * @param index The index of the element.
     * @return The right child of the element.
     */
    private T getRightChild(int index) {
        return getElement(getRightChildIndex(index));
    }

    /**
     * Returns the element at the specified index in the array.
     *
     * @param index The index of the element.
     * @return The element at the specified index.
     */
    @SuppressWarnings("unchecked")
    private T getElement(int index) {
        return (T) arr[index];
    }


    /**
     * Compares two elements using the provided comparator or natural ordering.
     *
     * @param obj1 The first element to be compared.
     * @param obj2 The second element to be compared.
     * @return A negative integer, zero, or a positive integer as the first element is less than, equal to, or greater
     * than the second.
     */
    private int compare(T obj1, T obj2) {
        if (obj1 == null || obj2 == null) {
            throw new IllegalArgumentException("Cannot compare null values in the heap.");
        }

        if (comparator != null) {
            return comparator.compare(obj1, obj2);
        } else {
            return obj1.compareTo(obj2);
        }
    }


    /**
     * Resizes the internal array to accommodate more elements.
     */
    private void resizeArray() {
        int newSize = arr.length * 2;
        Object[] newArray = new Object[newSize];
        System.arraycopy(arr, 0, newArray, 0, size);
        arr = newArray;
    }
}
