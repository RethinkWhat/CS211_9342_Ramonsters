package project2.backend.exceptions;

public class ListOverflowException extends Throwable {
    public ListOverflowException(){
        super("Desired index out of bounds");
    }
}
