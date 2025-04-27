package model.exceptions;

public class PessoaInvalidaException extends Exception {

  public PessoaInvalidaException() {
    super("Os dados informados não são válidos.");
  }

  public PessoaInvalidaException(String message) {
    super(message);
  }
}