package project2.backend;

/**
 * A generic class representing a node in a linked list.
 *
 * @param <T> The type of data stored in the node.
 */
public class Node<T> {
    /**
     * The data stored in the node.
     */
    public T data;

    /**
     * Reference to the next node in the linked list.
     */
    public Node<T> next;

    /**
     * Constructs a new Node with the given data and sets the next reference to null.
     *
     * @param data The data to be stored in the node.
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    /**
     * Constructs a new Node with the given data and a reference to the next node.
     *
     * @param data The data to be stored in the node.
     * @param next The reference to the next node in the linked list.
     */
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    /**
     * Gets the data stored in the node.
     *
     * @return The data stored in the node.
     */
    public T getData() {
        return data;
    }

    /**
     * Gets the reference to the next node in the linked list.
     *
     * @return The reference to the next node.
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Sets the data stored in the node.
     *
     * @param data The data to be stored in the node.
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Sets the reference to the next node in the linked list.
     *
     * @param next The reference to the next node.
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * This method overrides the default behavior of the equals method in the Object class.
     *
     * @param obj The object to compare for equality.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Returns a string representation of the node's data.
     *
     * @return A string representation of the node's data.
     */
    @Override
    public String toString() {
        return "" + this.data;
    }
}