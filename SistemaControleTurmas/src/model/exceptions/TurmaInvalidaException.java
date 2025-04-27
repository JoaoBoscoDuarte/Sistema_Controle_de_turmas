package model.exceptions;

public class TurmaInvalidaException extends Exception {

    public TurmaInvalidaException() {
        super("Turma inválida.");
    }

    public TurmaInvalidaException(String message) {
        super(message);
    }
}