package model.pessoa;

import model.disciplina.Disciplina;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*  ================| Só altere em caso de urgência! |====================
 *  --------------------Classe 100% concluída-------------------------> OK
 */

public class Professor extends Pessoa{
    private List<Disciplina> disciplinasMinistradas;

    public Professor(String nome, String telefone, String email) throws Exception {
        super(nome, telefone, email);
        disciplinasMinistradas = new ArrayList<>();
    }

    // Getters e setters --------------------------------------------> OK
    public List<Disciplina> getDisciplinasMinistradas() {
        return disciplinasMinistradas;
    }

    public void setDisciplinasMinistradas(List<Disciplina> disciplinasMinistradas) {
        this.disciplinasMinistradas = disciplinasMinistradas;
    }

    // Métodos básicos ----------------------------------------------> OK
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Professor professor = (Professor) o;
        return Objects.equals(disciplinasMinistradas, professor.disciplinasMinistradas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), disciplinasMinistradas);
    }

    @Override
    public String toString() {
        return "PROFESSOR: " +
                "| Nome: " + getNome() + " | \n" +
                "| Telefone: " + getTelefone() + " | \n" +
                "| Email: " + getEmail() + " | \n" +
                "| Matricula: " + getMatricula() + " | \n" +
                "| Disciplinas ministradas: " + disciplinasMinistradas.toString() + " | \n";
    }
}