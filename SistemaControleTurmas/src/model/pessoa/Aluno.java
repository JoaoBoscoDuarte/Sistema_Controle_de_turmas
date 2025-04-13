package model.pessoa;

import java.time.LocalDate;

public class Aluno extends Pessoa {
    public Aluno(String nome, String telefone, String email) throws Exception {
        super(nome, telefone, email);
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public void setNome(String nome) throws PessoaInvalidaException {
        super.setNome(nome);
    }

    @Override
    public String getTelefone() {
        return super.getTelefone();
    }

    @Override
    public void setTelefone(String telefone) throws PessoaInvalidaException {
        super.setTelefone(telefone);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) throws PessoaInvalidaException {
        super.setEmail(email);
    }

    @Override
    public boolean isAtivo() {
        return super.isAtivo();
    }

    @Override
    public void setAtivo(boolean ativo) {
        super.setAtivo(ativo);
    }

    @Override
    public String getMatricula() {
        return super.getMatricula();
    }

    @Override
    public LocalDate getDataCriacao() {
        return super.getDataCriacao();
    }

    @Override
    public void setDataCriacao(LocalDate dataCriacao) {
        super.setDataCriacao(dataCriacao);
    }

    @Override
    public void validar() {
        super.validar();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}