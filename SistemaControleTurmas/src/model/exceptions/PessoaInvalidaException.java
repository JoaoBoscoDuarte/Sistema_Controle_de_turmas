package model.exceptions;

public class PessoaInvalidaException extends Exception {
  public PessoaInvalidaException() {
    super("Aluno não encontrado ou não ativo.");
  }

  public PessoaInvalidaException(String message) {
    super(message);
  }
}


