package model.disciplina;

import model.exceptions.DisciplinaInvalidaException;
import model.pessoa.Professor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*  ================| Só altere em caso de urgência! |====================
 *  --------------------Classe 100% concluída-------------------------> OK
 */

public class Disciplina implements Serializable {
    private String nome;
    private int cargaHoraria;
    private String codigo;

    private List<Professor> professorAssociado = new ArrayList<>();
    private static int contador = 1;

    public Disciplina(String nome,String codigo, int cargaHoraria) throws DisciplinaInvalidaException {

        if (nome == null) {
            throw new DisciplinaInvalidaException("Nome da disciplina não pode ser nulo");
        }

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new DisciplinaInvalidaException("Codigo para a disciplina não pode ser nullo ou vazio");
        }

        this.nome = nome;
        this.codigo = codigo.replaceAll("\\s+", "").toUpperCase();
        this.cargaHoraria = cargaHoraria;
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

    public List<Professor> getProfessoresAssociados() {
        return professorAssociado;
    }

    public String getCodigo() {
        return codigo;
    }

    public String exibirProfessoresAssociados() {
        String exibir = "";
        for (Professor p : professorAssociado) {
            exibir += p.getNome() + " |";
        }

        return exibir;
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
        return Objects.hash(nome, cargaHoraria, codigo, professorAssociado);
    }

    @Override
    public String toString() {
        return "| Nome: " + getNomeDisciplina() + " | \n" +
                "| Carga horária: " + getCargaHoraria() + " | \n" +
                "| Codigo: " + getCodigo() + " | \n" +
                "| Professores associados: " + exibirProfessoresAssociados() + " |\n";
    }
}