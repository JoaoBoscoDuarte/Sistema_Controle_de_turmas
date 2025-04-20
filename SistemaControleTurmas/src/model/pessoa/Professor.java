package model.pessoa;
import model.exceptions.PessoaInvalidaException;
import model.turma.Turma;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Professor extends Pessoa{

    private List<Turma> turmasMinistradas;

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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Professor professor = (Professor) o;
        return Objects.equals(turmasMinistradas, professor.turmasMinistradas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), turmasMinistradas);
    }

    @Override
    public String toString() {
        return "Professor{" +
                "nome='" + getNome() + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", matricula='" + getMatricula() + '\'' +
                ", dataCriacao=" + getDataCriacao() +
                ", turmasMinistradas=" + turmasMinistradas +
                '}';
    }
}
