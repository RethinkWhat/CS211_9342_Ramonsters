package project2.backend;

public class Node<T> {
    public T data;
    public Node<T> next;

    public Node(T data){
        this.data = data;
        this.next = null;
    }

    public Node(T data, Node<T> next){
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "" + this.data;
    }
}
