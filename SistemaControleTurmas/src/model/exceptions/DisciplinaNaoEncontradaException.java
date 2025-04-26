package model.exceptions;

public class DisciplinaNaoEncontradaException extends Exception {

  public DisciplinaNaoEncontradaException() {
    super("Disciplina não encontrada.");
  }

  public DisciplinaNaoEncontradaException(String message) {
    super(message);
  }
}