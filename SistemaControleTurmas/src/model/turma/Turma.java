package model.turma;

import model.pessoa.Aluno;
import model.disciplina.Disciplina;
import model.pessoa.Professor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Turma {

    private Disciplina nomeDisciplina;
    private Professor nomeProfessor;
    private Map unidadesNotasAlunos; ///????????????????????????????
    private int capacidadeMaximaAlunos;
    private int capacidadeMinimaAlunos;
    private int qtdUnidadesAvaliativas;
    private LocalDate dataCriacaoTurma;
    private List<Aluno> alunos;
    private codigoTurma;

    public Turma(Disciplina nomeDisciplina, Professor nomeProfessor, Map unidadesNotasAlunos, int capacidadeMaximaAlunos, int capacidadeMinimaAlunos, int qtdUnidadesAvaliativas) {
        this.nomeDisciplina = nomeDisciplina;
        this.nomeProfessor = nomeProfessor;
        this.unidadesNotasAlunos = unidadesNotasAlunos;
        this.capacidadeMaximaAlunos = capacidadeMaximaAlunos;
        this.capacidadeMinimaAlunos = capacidadeMinimaAlunos;
        this.qtdUnidadesAvaliativas = qtdUnidadesAvaliativas;
        this.dataCriacaoTurma = LocalDate.now();
    }

    public int getCapacidadeMaximaAlunos() {
        return capacidadeMaximaAlunos;
    }

    public void setCapacidadeMaximaAlunos(int capacidadeMaximaAlunos) {
        this.capacidadeMaximaAlunos = capacidadeMaximaAlunos;
    }

    public int getCapacidadeMinimaAlunos() {
        return capacidadeMinimaAlunos;
    }

    public void setCapacidadeMinimaAlunos(int capacidadeMinimaAlunos) {
        this.capacidadeMinimaAlunos = capacidadeMinimaAlunos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return capacidadeMaximaAlunos == turma.capacidadeMaximaAlunos && capacidadeMinimaAlunos == turma.capacidadeMinimaAlunos && qtdUnidadesAvaliativas == turma.qtdUnidadesAvaliativas && Objects.equals(nomeDisciplina, turma.nomeDisciplina) && Objects.equals(nomeProfessor, turma.nomeProfessor) && Objects.equals(unidadesNotasAlunos, turma.unidadesNotasAlunos) && Objects.equals(dataCriacaoTurma, turma.dataCriacaoTurma) && Objects.equals(alunos, turma.alunos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeDisciplina, nomeProfessor, unidadesNotasAlunos, capacidadeMaximaAlunos, capacidadeMinimaAlunos, qtdUnidadesAvaliativas, dataCriacaoTurma, alunos);
    }

    @Override
    public String toString() {
        return "Turma{" +
                "Nome da Disciplina = " + nomeDisciplina +
                ", Nome Professor = " + nomeProfessor +
                ", Unidade de Notas dos alunos = " + unidadesNotasAlunos +
                ", Capacidade Máxima de alunos = " + capacidadeMaximaAlunos +
                ", Capacidade Mínima de alunos = " + capacidadeMinimaAlunos +
                ", Quantidade de Unidades Avaliativas = " + qtdUnidadesAvaliativas +
                ", Data de criação da turma = " + dataCriacaoTurma +
                ", Alunos = " + alunos +
                '}';
    }
}