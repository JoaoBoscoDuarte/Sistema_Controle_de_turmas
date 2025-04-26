package model.exceptions;

public class AssociacaoInvalidaException extends Exception {

  public AssociacaoInvalidaException() {
    super("Associação inválida.");
  }

  public AssociacaoInvalidaException(String message) {
    super(message);
  }
}
