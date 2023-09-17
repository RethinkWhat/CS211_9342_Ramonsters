package project2.backend;

import project2.backend.exceptions.ListOverflowException;
import project2.backend.interfaces.ListInterface;
import java.util.NoSuchElementException;

public class LinkedList<T> implements ListInterface {
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
    public void insert(Object data) throws ListOverflowException {
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
    public Object getElement(Object data) throws NoSuchElementException {
        Node<T> curr = this.head;

        while (curr != null){
            if (curr.getData().equals(data)){
                return curr;
            }
            curr = curr.getNext();
        }

        throw new NoSuchElementException();
    }

    @Override
    public boolean delete(Object data) {
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
    public int search(Object data) {
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
