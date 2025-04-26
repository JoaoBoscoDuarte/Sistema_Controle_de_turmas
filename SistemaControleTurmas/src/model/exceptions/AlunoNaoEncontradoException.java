package model.exceptions;

public class AlunoNaoEncontradoException extends Exception {

    public AlunoNaoEncontradoException() {
        super("Aluno não encontrado ou não ativo.");
    }

    public AlunoNaoEncontradoException(String message) {
        super(message);
    }
}