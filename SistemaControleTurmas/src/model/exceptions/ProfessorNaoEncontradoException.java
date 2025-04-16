package model.exceptions;

public class ProfessorNaoEncontradoException extends Exception {

    public ProfessorNaoEncontradoException(){
        super("Professor não encontrado ou se encontra desativado.");
    }

    public ProfessorNaoEncontradoException(String message) {
        super(message);
    }

}
