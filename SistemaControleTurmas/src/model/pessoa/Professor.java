package model.pessoa;
import model.exceptions.PessoaInvalidaException;
import model.turma.Turma;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Professor extends Pessoa{

    private List<Turma> turmasMinistradas = new ArrayList<>();

    public Professor(String nome, String telefone, String email, List<Turma> turmasMinistradas) throws Exception {
        super(nome, telefone, email);
        verificaTurmas(turmasMinistradas);
        this.turmasMinistradas = turmasMinistradas;
    }

    private static void verificaTurmas(List<Turma> turmasMinistradas) throws Exception{
        if(turmasMinistradas.isEmpty()){
            throw new Exception("A lista de turmas não se encontrar vazia");
        }
    }

    public List<Turma> getTurmasMinistradas() {
        return turmasMinistradas;
    }

    public void setTurmasMinistradas(List<Turma> turmasMinistradas) {
        this.turmasMinistradas = turmasMinistradas;
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
    public void invalidar() {
        super.invalidar();
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
