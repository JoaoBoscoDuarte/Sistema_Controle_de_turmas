package model.faculdade;

import model.disciplina.Disciplina;
import model.pessoa.Aluno;
import model.pessoa.Professor;
import model.turma.Turma;

import java.util.ArrayList;
import java.util.List;

public class Faculdade {
    List<Turma> colecaoDeTurmas;
    List<Professor> colecaoProfessor;
    List<Aluno> colecaoAluno;
    List<Disciplina> colecaoDisciplinas;

    public Faculdade() {
        this.colecaoDeTurmas = new ArrayList<>();
        this.colecaoProfessor = new ArrayList<>();
        this.colecaoAluno = new ArrayList<>();
        this.colecaoDisciplinas = new ArrayList<>();
    }

    public List<Turma> getColecaoDeTurmas() {
        return colecaoDeTurmas;
    }

    public void setColecaoDeTurmas(List<Turma> colecaoDeTurmas) {
        this.colecaoDeTurmas = colecaoDeTurmas;
    }

    public List<Professor> getColecaoProfessor() {
        return colecaoProfessor;
    }

    public void setColecaoProfessor(List<Professor> colecaoProfessor) {
        this.colecaoProfessor = colecaoProfessor;
    }

    public List<Aluno> getColecaoAluno() {
        return colecaoAluno;
    }

    public void setColecaoAluno(List<Aluno> colecaoAluno) {
        this.colecaoAluno = colecaoAluno;
    }

    public List<Disciplina> getColecaoDisciplinas() {
        return colecaoDisciplinas;
    }

    public void setColecaoDisciplinas(List<Disciplina> colecaoDisciplinas) {
        this.colecaoDisciplinas = colecaoDisciplinas;
    }
}