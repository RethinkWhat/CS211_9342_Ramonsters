package project2.backend.interfaces;

import java.util.NoSuchElementException;

public interface ListInterface<T> {
    public int getSize();
    public void insert(T data);
    public T getElement(T data) throws NoSuchElementException;
    public void delete(T data);
    public int search(T data);
}
