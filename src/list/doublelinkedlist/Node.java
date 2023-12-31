package list.doublelinkedlist;

/**
 * Node class for a double-linked list.
 *
 * @param <E> the type of data stored in the node
 */
public class Node<E> {

    private E data;
    private Node<E> next;
    private Node<E> prev;

    /**
     * Constructs a node with the specified data.
     *
     * @param data the data to be stored in the node
     */
    public Node(E data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    /**
     * Gets the data stored in the node.
     *
     * @return the data stored in the node
     */
    public E getData() {
        return data;
    }

    /**
     * Gets the next node in the list.
     *
     * @return the next node in the list
     */
    public Node<E> getNext() {
        return next;
    }

    /**
     * Sets the next node in the list.
     *
     * @param next the next node in the list
     */
    public void setNext(Node<E> next) {
        this.next = next;
    }

    /**
     * Gets the previous node in the list.
     *
     * @return the previous node in the list
     */
    public Node<E> getPrev() {
        return prev;
    }

    /**
     * Sets the previous node in the list.
     *
     * @param prev the previous node in the list
     */
    public void setPrev(Node<E> prev) {
        this.prev = prev;
    }

    /**
     * Sets the data in the node.
     *
     * @param data the data to be set in the node
     */
    public void setData(E data) {
        this.data = data;
    }
}
