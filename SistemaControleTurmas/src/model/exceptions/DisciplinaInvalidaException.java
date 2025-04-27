package model.exceptions;

public class DisciplinaInvalidaException extends Exception {

    public DisciplinaInvalidaException() {
        super("Disciplina inválida.");
    }

    public DisciplinaInvalidaException(String message) {
        super(message);
    }
}