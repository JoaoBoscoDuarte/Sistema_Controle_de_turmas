package model.exceptions;

public class IntervaloDeNotaException extends Exception {

    public IntervaloDeNotaException() {
        super("A nota deve estar entre 0 e 10.");
    }

    public IntervaloDeNotaException(String message) {
        super(message);
    }
}