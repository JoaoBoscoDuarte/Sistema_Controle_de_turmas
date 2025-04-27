package model.exceptions;

public class ProfessorNaoEncontradoException extends Exception {

    public ProfessorNaoEncontradoException(){
        super("Professor não encontrado ou não ativo.");
    }

    public ProfessorNaoEncontradoException(String message) {
        super(message);
    }
}