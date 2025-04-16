package model.pessoa;

import model.exceptions.PessoaInvalidaException;

public class Aluno extends Pessoa {
    public Aluno(String nome, String telefone, String email) throws PessoaInvalidaException {
        super(nome, telefone, email);
    }
}