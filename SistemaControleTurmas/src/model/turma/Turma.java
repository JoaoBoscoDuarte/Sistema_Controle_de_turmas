package model.turma;

import model.disciplina.Disciplina;
import model.exceptions.AlunoNaoEncontradoException;
import model.exceptions.TipoDeMediaNaoDefinidaException;
import model.pessoa.Professor;
import model.turma.media.TiposDeMediaIF;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Turma {

    private Disciplina disciplina;
    private Professor professor;
    private int numeroUnidades;
    private TiposDeMediaIF tipoDeMedia;

    // Coleção de alunos do tipo Nota (matricula --> notas)
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
        this.tipoDeMedia = null;
        this.numeroUnidades = 0;   // Valor incial
    }

    public double calcularMedia(String matricula) throws AlunoNaoEncontradoException, TipoDeMediaNaoDefinidaException {
        if (this.tipoDeMedia.equals(null)) {
            throw new TipoDeMediaNaoDefinidaException("O tipo de média da turma não foi definida. Configure o tipo de média.");
        }

        for (Nota n : notasAluno) {
            if (n.getMatricula().equals(matricula)) {
                return tipoDeMedia.calcularMedia(n.getNotasDoAluno());

            } else {
                throw new AlunoNaoEncontradoException("Aluno não encontrado na turma.");
            }
        }

        return 0;
    }

    public String verificarAprovacao(double media) {
        String exibir = "";

        if (media < 7) {
            exibir = "REPROVADO";

        } else if (media >= 7) {
            exibir = "APROVADO";
        }

        return exibir;
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
}