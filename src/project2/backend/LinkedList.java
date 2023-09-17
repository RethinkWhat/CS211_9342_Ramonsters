package project2.backend;

import project2.backend.interfaces.ListInterface;
import java.util.NoSuchElementException;

public class LinkedList<T> implements ListInterface<T> {
    private Node<T> head;

    private int size;

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void insert(T data) {
        Node<T> newNode = new Node(data);

        if (this.head == null){
            this.head = newNode;
        }else {
            newNode.setNext(this.head);
            this.head = newNode;
        }

        ++this.size;
    }

    @Override
    public T getElement(T data) throws NoSuchElementException {
        Node<T> curr = this.head;

        while (curr != null){
            if (curr.getData().equals(data)){
                return curr.data;
            }
            curr = curr.getNext();
        }

        throw new NoSuchElementException();
    }

    @Override
    public boolean delete(T data) {
        Node<T> curr = this.head;
        Node<T> prev = null;

        while (curr != null){
            if (curr.getData().equals(data)){
                if (prev == null){
                    this.head = curr.getNext();
                }else {
                    prev.setNext(curr.getNext());
                }
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

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

    public Node<T> get(int index) {
        Node<T> tempNode = head;
        for (int i =0; i <this.size; i++) {
            if (i==index) {
                return tempNode;
            }
            tempNode = tempNode.getNext();
        }
        return null;
    }
}
