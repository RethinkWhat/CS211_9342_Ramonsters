package project2.backend.interfaces;

import java.util.NoSuchElementException;

public interface ListInterface<T> {
    public int getSize();
    public void insert(T data) throws ListOverflowException;
    public T getElement(T data) throws NoSuchElementException;
    public boolean delete(T data);
    public int search(T data);
}
