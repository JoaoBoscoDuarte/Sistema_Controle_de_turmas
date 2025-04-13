package model.turma;

import model.disciplina.Disciplina;
import model.pessoa.Aluno;
import model.pessoa.Professor;
import model.turma.media.TiposDeMediaIF;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Turma {

    private Disciplina disciplina;
    private Professor professor;
    private int qtdUnidadesAvaliativas;

    private List<Nota> notasAluno;

    private TiposDeMediaIF tipoDeMedia;
    private LocalDate dataCriacaoTurma;
    private String codigoTurma;
    private static int contador = 0;

    public Turma(Disciplina disciplina, Professor professor, int qtdUnidadesAvaliativas, TiposDeMediaIF tipoDeMedia) {
        this.disciplina = disciplina;
        this.professor = professor;
        this.qtdUnidadesAvaliativas = qtdUnidadesAvaliativas;
        this.tipoDeMedia = tipoDeMedia;
        this.notasAluno = new ArrayList<>();
        this.dataCriacaoTurma = LocalDate.now();
        this.codigoTurma = geraCodigoTurma();
    }

    public double calcularMedia(Aluno aluno) {
        for (Nota nota : notasAluno) {
            if (nota.getAluno().equals(aluno)) {
                tipoDeMedia.calcularMedia(nota.getNotasDoAluno());
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

    public String formataData() {
        return dataCriacaoTurma.format(DateTimeFormatter.ofPattern("ddMMyyyy"));
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
        return qtdUnidadesAvaliativas == turma.qtdUnidadesAvaliativas && Objects.equals(disciplina, turma.disciplina) && Objects.equals(professor, turma.professor) && Objects.equals(dataCriacaoTurma, turma.dataCriacaoTurma) && Objects.equals(codigoTurma, turma.codigoTurma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(disciplina, professor, qtdUnidadesAvaliativas, dataCriacaoTurma, codigoTurma);
    }

    @Override
    public String toString() {
        return "Turma{" +
                "nomeDisciplina=" + disciplina +
                ", nomeProfessor=" + professor +
                ", qtdUnidadesAvaliativas=" + qtdUnidadesAvaliativas +
                ", dataCriacaoTurma=" + dataCriacaoTurma +
                ", codigoTurma='" + codigoTurma + '\'' +
                '}';
    }
}