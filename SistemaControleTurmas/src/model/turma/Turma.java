package model.turma;

import model.disciplina.Disciplina;
import model.pessoa.Professor;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Objects;

public class Turma {

    private Disciplina nomeDisciplina;
    private Professor nomeProfessor;
    private int qtdUnidadesAvaliativas;
    private LocalDate dataCriacaoTurma;
    private String codigoTurma;
    private static int contador = 0;

    public Turma(Disciplina nomeDisciplina, Professor nomeProfessor, int qtdUnidadesAvaliativas) {
        this.nomeDisciplina = nomeDisciplina;
        this.nomeProfessor = nomeProfessor;
        this.qtdUnidadesAvaliativas = qtdUnidadesAvaliativas;
        this.dataCriacaoTurma = LocalDate.now();
        this.codigoTurma = geraCodigoTurma();
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
        return qtdUnidadesAvaliativas == turma.qtdUnidadesAvaliativas && Objects.equals(nomeDisciplina, turma.nomeDisciplina) && Objects.equals(nomeProfessor, turma.nomeProfessor) && Objects.equals(dataCriacaoTurma, turma.dataCriacaoTurma) && Objects.equals(codigoTurma, turma.codigoTurma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeDisciplina, nomeProfessor, qtdUnidadesAvaliativas, dataCriacaoTurma, codigoTurma);
    }

    @Override
    public String toString() {
        return "Turma{" +
                "nomeDisciplina=" + nomeDisciplina +
                ", nomeProfessor=" + nomeProfessor +
                ", qtdUnidadesAvaliativas=" + qtdUnidadesAvaliativas +
                ", dataCriacaoTurma=" + dataCriacaoTurma +
                ", codigoTurma='" + codigoTurma + '\'' +
                '}';
    }
}