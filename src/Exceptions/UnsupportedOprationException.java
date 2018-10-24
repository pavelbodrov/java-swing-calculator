package Exceptions;
// needed when we add new operation to GUI but don`t set it`s realisation
public class UnsupportedOprationException extends Exception {
    public UnsupportedOprationException() {
        super("Unsupported operation!");
    }
}
