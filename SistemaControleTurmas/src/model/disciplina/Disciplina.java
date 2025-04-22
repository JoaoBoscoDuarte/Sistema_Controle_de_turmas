package model.disciplina;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*  ================| Só altere em caso de urgência! |====================
 *  --------------------Classe 100% concluída-------------------------> OK
 */

public class Disciplina {
    private String nome;
    private int cargaHoraria;
    private String codigo;

    private List<String> professorAssociado = new ArrayList<>();
    private static int contador = 1;

    public Disciplina(String nome, int cargaHoraria) {
        this.nome = nome;
        this.codigo = gerarCodigoDisciplina();
        this.cargaHoraria = cargaHoraria;
    }

    public void adicionarProfessorAssociado(String codigoProfessor) {
        this.professorAssociado.add(codigoProfessor);
    }

    // Getters e setters --------------------------------------------> OK
    public String getNomeDisciplina() {
        return nome;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nome = nomeDisciplina;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
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

    // Métodos básicos ----------------------------------------------> OK
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Disciplina that = (Disciplina) o;
        return cargaHoraria == that.cargaHoraria && Objects.equals(nome, that.nome) && Objects.equals(codigo, that.codigo) && Objects.equals(professorAssociado, that.professorAssociado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, codigo, cargaHoraria, professorAssociado);
    }

    @Override
    public String toString() {
        return "DISCIPLINA: \n" +
                "| Nome: " + getNomeDisciplina() + " | \n" +
                "| Carga horária: " + getCargaHoraria() + " | \n" +
                "| Codigo: " + getCodigo() + " | \n" +
                "| Professores associados: " + String.join(", ", professorAssociado) + " |";
    }
}
