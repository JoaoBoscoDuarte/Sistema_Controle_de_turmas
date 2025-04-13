package model.faculdade;

import model.pessoa.Aluno;
import model.pessoa.Professor;
import model.turma.Turma;

import java.util.ArrayList;
import java.util.List;

public class Faculdade {
    List<Turma> colecaoDeTurmas;
    List<Professor> colecaoProfessor;
    List<Aluno> colecaoAluno;

    public Faculdade() {
        this.colecaoDeTurmas = new ArrayList<>();
        this.colecaoProfessor = new ArrayList<>();
        this.colecaoAluno = new ArrayList<>();
    }
}
