package model.turma;

import model.disciplina.Disciplina;
import model.pessoa.Professor;
import model.turma.media.MediaSimples;
import model.turma.media.TiposDeMediaIF;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*  ================| Só altere em caso de urgência! |====================
 *  ----------------------Classe 100% concluída-----------------------> OK
 */

public class Turma implements Serializable {
    private static final long serialVersionUID = 1L;

    private Disciplina disciplina;
    private Professor professor;
    private int numeroUnidades;
    private TiposDeMediaIF tipoDeMedia;

    // Temos uma lista Nota | Nota é uma classe que guarda uma matrícula (referente a uma aluno) e suas notas
    // Logo, temos Nota (aponta para um aluno único) e NotasAluno (aponta para todas as notas dos alunos)
    private List<Nota> notasAluno;
    private List<String> matriculasProfessores;

    private String codigoTurma;
    private static int contador = 0;

    public Turma(Disciplina disciplina, Professor professor) {
        this.disciplina = disciplina;
        this.professor = professor;
        this.notasAluno = new ArrayList<>();
        this.matriculasProfessores = new ArrayList<>();
        this.codigoTurma = geraCodigoTurma();
        this.tipoDeMedia = new MediaSimples();           // valor padrão
        this.numeroUnidades = 0;                         // Valor padrão
    }

    public static String geraCodigoTurma() {
        return LocalDate.now().getYear() + String.format("%02d" , contador++);
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public int getNumeroUnidades() {
        return numeroUnidades;
    }

    public void setNumeroUnidades(int numeroUnidades) {
        this.numeroUnidades = numeroUnidades;
    }

    public TiposDeMediaIF getTipoDeMedia() {
        return tipoDeMedia;
    }

    public void setTipoDeMedia(TiposDeMediaIF tipoDeMedia) {
        this.tipoDeMedia = tipoDeMedia;
    }

    public List<Nota> getNotasAluno() {
        return notasAluno;
    }

    public void setNotasAluno(List<Nota> notasAluno) {
        this.notasAluno = notasAluno;
    }

    public List<String> getMatriculasProfessores() {
        return matriculasProfessores;
    }

    public void setMatriculasProfessores(List<String> matriculasProfessores) {
        this.matriculasProfessores = matriculasProfessores;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public void setCodigoTurma(String codigoTurma) {
        this.codigoTurma = codigoTurma;
    }

    @Override
    public String toString() {
        return "TURMA: " +
                "| Disciplina associada: " + disciplina.getNomeDisciplina() + " | \n" +
                "| Professor: " + professor.getNome() + " | \n" +
                "| Nº Unidades Avaliativas: " + getNumeroUnidades() + " | \n" +
                "| Tipo de Média: " + getTipoDeMedia() + " | \n" +
                "| Alunos associados: " + getNotasAluno().toString() + " | \n" +
                "| Codigo da turma: " + getCodigoTurma() + " | \n";
    }
}