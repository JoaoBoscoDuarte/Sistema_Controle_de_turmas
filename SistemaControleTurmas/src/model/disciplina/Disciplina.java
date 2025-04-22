package model.disciplina;

import model.pessoa.Professor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Disciplina {
    private String nomeDisciplina;
    private int cargaHoraria;
    private String codigo;

    private List<String> professorAssociado = new ArrayList<>();
    private static int contador = 1;

    public Disciplina(String nomeDisciplina, int cargaHoraria) {
        this.nomeDisciplina = nomeDisciplina;
        this.codigo = gerarCodigoDisciplina();
        this.cargaHoraria = cargaHoraria;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public void adicionarProfessorAssociado(String codigoProfessor) {
        this.professorAssociado.add(codigoProfessor);
    }

    public List<String> getProfessoresAssociados() {
        return professorAssociado;
    }

    public void setProfessorAssociado(List<String> professorAssociado) {
        this.professorAssociado = professorAssociado;
    }

    public String gerarCodigoDisciplina() {
        return LocalDate.now().getYear() + String.format("%04d" , contador++);
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Disciplina that = (Disciplina) o;
        return cargaHoraria == that.cargaHoraria && Objects.equals(nomeDisciplina, that.nomeDisciplina) && Objects.equals(codigo, that.codigo) && Objects.equals(professorAssociado, that.professorAssociado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeDisciplina, codigo, cargaHoraria, professorAssociado);
    }

    @Override
    public String toString() {
        return "DISCIPLINA: \n" +
                "| Nome: " + getNomeDisciplina() + " | \n" +
                "| Carga horária: " + getCargaHoraria() + " | \n" +
                "| Codigo: " + getCodigo() + " | \n" +
                "| Professor associado: " + getProfessoresAssociados().toString() + " | \n";
    }
}
