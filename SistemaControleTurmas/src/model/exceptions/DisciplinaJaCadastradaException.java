package model.exceptions;

public class DisciplinaJaCadastradaException extends Exception {

  public DisciplinaJaCadastradaException() {
    super("Disciplina de mesmo nome já cadastrada.");
  }

  public DisciplinaJaCadastradaException(String message) {
    super(message);
  }
}