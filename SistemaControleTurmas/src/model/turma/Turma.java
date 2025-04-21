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
    private int numeroUnidades = 2;        // Por padrão são 2 unidades (pode ser mudado somente 1 vez)
    private TiposDeMediaIF tipoDeMedia;

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

    public void setNumeroUnidades(int numeroUnidades) {
        this.numeroUnidades = numeroUnidades;
    }

    public List<String> getMatriculasProfessores() {
        return matriculasProfessores;
    }

    public void setMatriculasProfessores(List<String> matriculasProfessores) {
        this.matriculasProfessores = matriculasProfessores;
    }

    public static String geraCodigoTurma() {
        return LocalDate.now().getYear() + String.format("%02d" , contador++);
    }

    public TiposDeMediaIF getTipoDeMedia() {
        return tipoDeMedia;
    }

    public void setTipoDeMedia(TiposDeMediaIF tipoDeMedia) {
        this.tipoDeMedia = tipoDeMedia;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public List<Nota> getNotasAluno(String matricula) {

    }

    public int getNumeroUnidades() {
        return numeroUnidades;
    }
}