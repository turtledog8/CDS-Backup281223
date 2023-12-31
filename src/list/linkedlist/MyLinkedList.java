package list.linkedlist;

import list.List;

import java.util.Iterator;
/**
 * Implementation of a linked list.
 *
 * @param <T> the type of data stored in the list
 */
public class MyLinkedList<T> implements List<T>, Iterable<T> {
    private Node<T> head;
    private int size;


    /**
     * Constructs an empty linked list.
     */
    public MyLinkedList() {
        this.head = null;
        this.size = 0;
    }
    /**
     * Adds an element to the end of the linked list.
     *
     * @param element the element to be added
     */
    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);

        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        size++;
    }
    /**
     * Adds an element at the specified index in the linked list.
     *
     * @param index   the index at which the element will be added
     * @param element the element to be added
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> newNode = new Node<>(element);

        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }

        size++;
    }
    /**
     * Removes the first occurrence of the specified element from the linked list.
     *
     * @param element the element to be removed
     */
    @Override
    public void remove(T element) {
        if (head == null) {
            return;
        }

        if (head.data.equals(element)) {
            head = head.next;
            size--;
            return;
        }

        Node<T> current = head;
        while (current.next != null && !current.next.data.equals(element)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
            size--;
        }
    }
    /**
     * Removes the element at the specified index from the linked list.
     *
     * @param index the index of the element to be removed
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            head = head.next;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }

        size--;
    }
    /**
     * Retrieves the element at the specified index from the linked list.
     *
     * @param index the index of the element to be retrieved
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }
    /**
     * Checks if the linked list contains the specified element.
     *
     * @param element the element to be checked
     * @return true if the linked list contains the element, otherwise false
     */
    @Override
    public boolean contains(T element) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    /**
     * Returns the size of the linked list.
     *
     * @return the size of the linked list
     */
    @Override
    public int size() {
        return size;
    }
    /**
     * Checks if the linked list is empty.
     *
     * @return true if the linked list is empty, otherwise false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * Clears the linked list.
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Sets the element at the specified index in the linked list.
     *
     * @param index   the index at which the element will be set
     * @param element the element to be set
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        current.data = element;
    }

    /**
     * Returns an iterator over the elements in the linked list.
     *
     * @return an iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
    /**
     * Reverses the linked list.
     *
     * @return a new linked list with elements in reverse order
     */
    public MyLinkedList<T> reverse() {
        MyLinkedList<T> reversedList = new MyLinkedList<>();
        Node<T> current = head;

        while (current != null) {
            reversedList.add(0, current.data);
            current = current.next;
        }

        return reversedList;
    }

    /**
     * Node class for the linked list.
     *
     * @param <T> the type of data stored in the node
     */    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Returns a string representation of the linked list.
     *
     * @return a string representation of the linked list
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<T> current = head;

        while (current != null) {
            result.append(current.data);
            if (current.next != null) {
                result.append(", ");
            }
            current = current.next;
        }

        result.append("]");
        return result.toString();
    }


}