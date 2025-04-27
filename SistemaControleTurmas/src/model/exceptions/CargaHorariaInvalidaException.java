package model.exceptions;

public class CargaHorariaInvalidaException extends Exception {

    public CargaHorariaInvalidaException() {
        super("A carga horária informada é inválida.");
    }

    public CargaHorariaInvalidaException(String message) {
        super(message);
    }
}