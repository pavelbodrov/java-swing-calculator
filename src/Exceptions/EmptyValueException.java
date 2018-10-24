package Exceptions;

public class EmptyValueException extends Exception {
    public EmptyValueException() {
        super("Value is empty! Enter something!");
    }
}