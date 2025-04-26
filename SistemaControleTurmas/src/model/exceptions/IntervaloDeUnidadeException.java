package model.exceptions;

public class IntervaloDeUnidadeException extends Exception {

    public IntervaloDeUnidadeException() {
        super("O número de unidades informado é inválido");
    }

    public IntervaloDeUnidadeException(String message) {
        super(message);
    }
}
