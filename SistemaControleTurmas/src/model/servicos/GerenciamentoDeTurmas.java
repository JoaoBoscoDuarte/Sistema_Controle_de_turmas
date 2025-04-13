package model.servicos;

import model.disciplina.Disciplina;
import model.pessoa.Aluno;
import model.pessoa.Pessoa;
import model.pessoa.Professor;
import model.turma.Turma;
import model.turma.media.TiposDeMediaIF;

import java.util.ArrayList;
import java.util.List;

public class GerenciamentoDeTurmas {
    private List<Turma> turmas;
    private GerenciamentoDeAlunos gerenciamentoDeAlunos;

    public GerenciamentoDeTurmas(GerenciamentoDeAlunos gerenciamentoDeAlunos) {
        this.turmas = new ArrayList<>();
        this.gerenciamentoDeAlunos = new GerenciamentoDeAlunos();
    }

    public void criarTurma(Disciplina disciplina, Professor professor, int qtdUnidadesAvaliativas, TiposDeMediaIF tipoDeMedia) {
        Turma turma = new Turma(disciplina, professor, qtdUnidadesAvaliativas, tipoDeMedia);
        turmas.add(turma);
    }

    public void adicionarAlunoATurma(String matricula, Turma turma) {
        if (gerenciamentoDeAlunos.existeAluno(matricula)) {
            turma.adicionarAluno(matricula);
        }
    }

    //public void adicionarProfessorATurma(String)

    public void atribuirQuantidadeDeUnidadesAvaliativas(int qtdUnidadesAvaliativas, Turma turma) {
        for (Turma t : turmas) {
            if (t.equals(turma)) {
                t.setQtdUnidadesAvaliativas(qtdUnidadesAvaliativas);
            }
        }
    }

//    public void cadastrarNotas(Aluno aluno, int Unidade, double nota) {
//        String matricula = aluno.getMatricula();
//        List<Aluno> alunos = turmas.
//
//        for ()
//    }
}
