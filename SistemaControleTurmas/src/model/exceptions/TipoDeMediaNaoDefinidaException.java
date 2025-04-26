package model.exceptions;

public class TipoDeMediaNaoDefinidaException extends Exception {

    public TipoDeMediaNaoDefinidaException() {
        super("O tipo de média não foi definido.");
    }

    public TipoDeMediaNaoDefinidaException(String message) {
        super(message);
    }
}