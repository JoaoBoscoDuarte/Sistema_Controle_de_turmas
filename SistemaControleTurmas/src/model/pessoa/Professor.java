package model.pessoa;
import model.turma.Turma;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Professor extends Pessoa{

    private List<Turma> turmasMinistradas = new ArrayList<>();

    public Professor(String nome, String telefone, String email, List<Turma> turmasMinistradas) throws Exception {
        super(nome, telefone, email);
        this.turmasMinistradas = turmasMinistradas;
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
    public void setNome(String nome) {
        super.setNome(nome);
    }

    @Override
    public String getTelefone() {
        return super.getTelefone();
    }

    @Override
    public void setTelefone(String telefone) {
        super.setTelefone(telefone);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
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
