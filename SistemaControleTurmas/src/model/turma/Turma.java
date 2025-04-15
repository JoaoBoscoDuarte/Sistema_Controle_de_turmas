package model.turma;

import model.disciplina.Disciplina;
import model.pessoa.Aluno;
import model.pessoa.Professor;
import model.turma.media.TiposDeMediaIF;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Turma {

    private Disciplina disciplina;
    private Professor professor;
    private int qtdUnidadesAvaliativas = 2;

    // A lista de notas e matrículas de alunos estão associadas pelo índice, ou seja: a nota
    // e o aluno correspondem ao índice específico
    private List<Nota> notasAluno;
    private List<String> matriculasAlunos;
    private List<String> matriculasProfessores;

    private TiposDeMediaIF tipoDeMedia;
    private LocalDate dataCriacaoTurma;
    private String codigoTurma;
    private static int contador = 0;

    public Turma(Disciplina disciplina, Professor professor) {
        this.disciplina = disciplina;
        this.professor = professor;
        this.notasAluno = new ArrayList<>();
        this.matriculasAlunos = new ArrayList<>();
        this.matriculasProfessores = new ArrayList<>();
        this.dataCriacaoTurma = LocalDate.now();
        this.codigoTurma = geraCodigoTurma();
    }

    public double calcularMedia(Aluno aluno, TiposDeMediaIF tipoDeMedia) {
        for (Nota nota : notasAluno) {
            if (nota.getAluno().equals(aluno)) {
                Map<Integer, Double> notasPorUnidade = nota.getNotasPorUnidade(); // pega todas as nota s
                List<Double> todasNotas = new ArrayList<>(notasPorUnidade.values()); // passa de map para arraylist (double)
                return tipoDeMedia.calcularMedia(todasNotas); // calcula a media de acordo com a media passada no parâmetro
            }
        }
        throw new IllegalArgumentException("Aluno não encontrado na turma");
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

    public int getQtdUnidadesAvaliativas() {
        return qtdUnidadesAvaliativas;
    }

    public void setQtdUnidadesAvaliativas(int qtdUnidadesAvaliativas) {
        this.qtdUnidadesAvaliativas = qtdUnidadesAvaliativas;
    }

    public List<String> getMatriculasAlunos() {
        return new ArrayList<>(matriculasAlunos);
    }

    public void setMatriculasAlunos(List<String> matriculasAlunos) {
        this.matriculasAlunos = matriculasAlunos;
    }

    public List<String> getMatriculasProfessores() {
        return matriculasProfessores;
    }

    public void setMatriculasProfessores(List<String> matriculasProfessores) {
        this.matriculasProfessores = matriculasProfessores;
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

    public List<Nota> getNotasAluno() {
        return notasAluno;
    }

    public static String geraCodigoTurma() {
        contador++;
        String dataFormatada = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        return String.format("%s-%05d", dataFormatada, contador);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return qtdUnidadesAvaliativas == turma.qtdUnidadesAvaliativas && Objects.equals(disciplina, turma.disciplina) && Objects.equals(professor, turma.professor) && Objects.equals(notasAluno, turma.notasAluno) && Objects.equals(matriculasAlunos, turma.matriculasAlunos) && Objects.equals(matriculasProfessores, turma.matriculasProfessores) && Objects.equals(tipoDeMedia, turma.tipoDeMedia) && Objects.equals(dataCriacaoTurma, turma.dataCriacaoTurma) && Objects.equals(codigoTurma, turma.codigoTurma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(disciplina, professor, qtdUnidadesAvaliativas, notasAluno, matriculasAlunos, matriculasProfessores, tipoDeMedia, dataCriacaoTurma, codigoTurma);
    }

    @Override
    public String toString() {
        return "Turma{" +
                "disciplina=" + disciplina +
                ", professor=" + professor +
                ", qtdUnidadesAvaliativas=" + qtdUnidadesAvaliativas +
                ", notasAluno=" + notasAluno +
                ", matriculasAlunos=" + matriculasAlunos +
                ", matriculasProfessores=" + matriculasProfessores +
                ", tipoDeMedia=" + tipoDeMedia +
                ", dataCriacaoTurma=" + dataCriacaoTurma +
                ", codigoTurma='" + codigoTurma + '\'' +
                '}';
    }
}