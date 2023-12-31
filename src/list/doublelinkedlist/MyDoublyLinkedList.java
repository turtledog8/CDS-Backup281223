package list.doublelinkedlist;

import list.List;

import java.util.Iterator;

/**
 * Implementation of a double-linked list.
 *
 * @param <E> the type of elements in the list
 */
public class MyDoublyLinkedList<E> implements List<E>, Iterable<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * Constructs an empty doubly linked list.
     */
    public MyDoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element the element to be added
     */
    @Override
    public void add(E element) {
        Node<E> newNode = new Node<>(element);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        size++;
    }

    /**
     * Adds an element at a specific index in the list.
     *
     * @param index   the index at which the element is to be added
     * @param element the element to be added
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<E> newNode = new Node<>(element);
        if (index == 0) {
            newNode.setNext(head);
            if (head != null) {
                head.setPrev(newNode);
            }
            head = newNode;
        } else if (index == size) {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        } else {
            Node<E> current = getNode(index);
            newNode.setNext(current);
            newNode.setPrev(current.getPrev());
            current.getPrev().setNext(newNode);
            current.setPrev(newNode);
        }
        size++;
    }

    /**
     * Removes a specific element from the list.
     *
     * @param element the element to be removed
     */
    @Override
    public void remove(E element) {
        Node<E> current = head;
        while (current != null) {
            if (current.getData().equals(element)) {
                removeNode(current);
                return;
            }
            current = current.getNext();
        }
    }

    /**
     * Removes the element at a specific index from the list.
     *
     * @param index the index at which the element is to be removed
     */
    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<E> current = getNode(index);
        removeNode(current);
    }

    private void removeNode(Node<E> node) {
        if (node.getPrev() == null) {
            head = node.getNext();
        } else {
            node.getPrev().setNext(node.getNext());
        }

        if (node.getNext() == null) {
            tail = node.getPrev();
        } else {
            node.getNext().setPrev(node.getPrev());
        }

        size--;
    }

    /**
     * Gets the element at a specific index in the list.
     *
     * @param index the index of the element to be retrieved
     * @return the element at the specified index
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return getNode(index).getData();
    }

    private Node<E> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }

    /**
     * Checks if the list contains a specific element.
     *
     * @param element the element to be checked for existence
     * @return true if the element is in the list, false otherwise
     */
    @Override
    public boolean contains(E element) {
        Node<E> current = head;
        while (current != null) {
            if (current.getData().equals(element)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Gets the size of the list.
     *
     * @return the number of elements in the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears all elements from the list.
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Sets the element at a specific index in the list.
     *
     * @param index the index at which the element is to be set
     * @param temp  the element to set at the specified index
     */
    @Override
    public void set(int index, E temp) {
        // Method intentionally left empty.
    }

    /**
     * Provides an iterator for the doubly linked list.
     *
     * @return an iterator for the doubly linked list
     */
    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    /**
     * Swaps the elements at the specified indices in the list.
     *
     * @param index1 the index of the first element
     * @param index2 the index of the second element
     */
    public void swap(int index1, int index2) {
        if (index1 < 0 || index1 >= size || index2 < 0 || index2 >= size) {
            throw new IndexOutOfBoundsException("Invalid indices");
        }

        if (index1 == index2) {
            return; // No need to swap if indices are the same
        }

        Node<E> node1 = getNode(index1);
        Node<E> node2 = getNode(index2);

        // Swap the elements
        E temp = node1.getData();
        node1.setData(node2.getData());
        node2.setData(temp);
    }
    /**
     * Iterator implementation for the doubly linked list.
     */
    private class LinkedListIterator implements Iterator<E> {
        private Node<E> current = head;

        /**
         * Checks if there is a next element in the iterator.
         *
         * @return true if there is a next element, false otherwise
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Gets the next element in the iterator.
         *
         * @return the next element in the iterator
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            E data = current.getData();
            current = current.getNext();
            return data;
        }
    }

    /**
     * Generates a string representation of the doubly linked list.
     *
     * @return a string representation of the doubly linked list
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<E> current = head;
        while (current != null) {
            result.append(current.getData());
            if (current.getNext() != null) {
                result.append(", ");
            }
            current = current.getNext();
        }
        result.append("]");
        return result.toString();
    }

}
