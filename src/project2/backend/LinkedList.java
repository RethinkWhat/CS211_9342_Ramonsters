package project2.backend;

import project2.backend.interfaces.ListInterface;
import java.util.NoSuchElementException;

/**
 * A linked list implementation that implements the ListInterface.
 *
 * @param <T> The type of elements stored in the linked list.
 */
public class LinkedList<T> implements ListInterface<T> {
    private Node<T> head;
    private int size;

    /**
     * Get the head node of the linked list.
     *
     * @return The head node of the linked list.
     */
    public Node<T> getHead() {
        return head;
    }

    /**
     * Set the head node of the linked list.
     *
     * @param head The new head node to be set.
     */
    public void setHead(Node<T> head) {
        this.head = head;
    }

    /**
     * Get the current size of the linked list.
     *
     * @return The current size of the linked list.
     */
    @Override
    public int getSize() {
        return this.size;
    }

    /**
     * Insert a new element at the beginning of the linked list.
     *
     * @param data The data to be inserted.
     */
    @Override
    public void insert(T data){
        Node<T> newNode = new Node<T>(data);

        if (this.head == null){
            this.head = newNode;
        } else {
            newNode.setNext(this.head);
            this.head = newNode;
        }

        ++this.size;
    }

    /**
     * Retrieve an element from the linked list based on the provided data.
     *
     * @param data The data to search for in the linked list.
     * @return The element found with the provided data.
     * @throws NoSuchElementException If the specified data is not found in the list.
     */
    @Override
    public T getElement(T data) throws NoSuchElementException {
        Node<T> curr = this.head;

        while (curr != null){
            if (curr.getData().equals(data)){
                return curr.getData();
            }
            curr = curr.getNext();
        }

        throw new NoSuchElementException();
    }

    /**
     * Delete the first occurrence of an element from the linked list based on the provided data.
     *
     * @param data The data of the element to be deleted.
     * @return True if an element with the provided data is found and deleted, false otherwise.
     */
    @Override
    public boolean delete(T data) {
        Node<T> curr = this.head;
        Node<T> prev = null;

        while (curr != null){
            if (curr.getData().equals(data)){
                if (prev == null){
                    this.head = curr.getNext();
                } else {
                    prev.setNext(curr.getNext());
                }
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    /**
     * Search for an element in the linked list and return its index.
     *
     * @param data The data of the element to search for.
     * @return The index of the element in the linked list, or -1 if not found.
     */
    @Override
    public int search(T data) {
        Node<T> curr = this.head;

        for (int i = 0; i < this.size; i++) {
            if (curr.getData().equals(data)) {
                return i;
            }
            curr = curr.getNext();
        }

        return -1;
    }
}
