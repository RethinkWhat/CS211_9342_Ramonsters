package project2.backend.interfaces;

import java.util.NoSuchElementException;

/**
 * An interface representing a generic list data structure.
 *
 * @param <T> The type of elements in the list.
 */
public interface ListInterface<T> {
    /**
     * Gets the size (number of elements) of the list.
     *
     * @return The size of the list.
     */
    public int getSize();

    /**
     * Inserts an element into the list.
     *
     * @param data The data to be inserted into the list.
     */
    public void insert(T data);

    /**
     * Retrieves an element from the list based on its data.
     *
     * @param data The data to search for in the list.
     * @return The element with the specified data.
     * @throws NoSuchElementException If the element with the specified data is not found.
     */
    public T getElement(T data) throws NoSuchElementException;

    /**
     * Deletes an element from the list based on its data.
     *
     * @param data The data of the element to be deleted from the list.
     */
    public void delete(T data);

    /**
     * Searches for an element in the list and returns its position/index.
     *
     * @param data The data to search for in the list.
     * @return The position/index of the element with the specified data, or -1 if not found.
     */
    public int search(T data);
}