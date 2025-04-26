package model.exceptions;

public class NomeDaDisciplinaInvalidoException extends Exception {

    public NomeDaDisciplinaInvalidoException() {
        super("O nome da disciplina não pode estar vazio.");
    }

    public NomeDaDisciplinaInvalidoException(String message) {
        super(message);
    }
}