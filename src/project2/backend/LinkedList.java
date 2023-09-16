package project2.backend;

import project2.backend.exceptions.ListOverflowException;
import project2.backend.interfaces.ListInterface;

import java.util.NoSuchElementException;

public class LinkedList<T> implements ListInterface<T> {
    private Node<T> head;

    private int size;

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void insert(Object data) throws ListOverflowException {

    }

    @Override
    public Object getElement(Object data) throws NoSuchElementException {
        return null;
    }

    @Override
    public boolean delete(Object data) {
        return false;
    }

    @Override
    public int search(Object data) {
        return 0;
    }
}
