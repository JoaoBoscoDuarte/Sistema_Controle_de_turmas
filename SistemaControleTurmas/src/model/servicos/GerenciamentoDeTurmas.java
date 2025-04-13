package model.servicos;

import model.disciplina.Disciplina;
import model.pessoa.Aluno;
import model.pessoa.Pessoa;
import model.pessoa.Professor;
import model.turma.Turma;
import model.turma.media.TiposDeMediaIF;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.ArrayList;
import java.util.List;

public class GerenciamentoDeTurmas {
    private List<Turma> turmas;
    private GerenciamentoDeAlunos gerenciamentoDeAlunos;

    public GerenciamentoDeTurmas(GerenciamentoDeAlunos gerenciamentoDeAlunos) {
        this.turmas = new ArrayList<>();
        this.gerenciamentoDeAlunos = new GerenciamentoDeAlunos;
    }

    public void criarTurma(Disciplina disciplina, Professor professor, int qtdUnidadesAvaliativas, TiposDeMediaIF tipoDeMedia) {
        Turma turma = new Turma(disciplina, professor, qtdUnidadesAvaliativas, tipoDeMedia);
        turmas.add(turma);
    }

    public void adicionaarAlunoATurma(String matricula, Turma turma) {
        if (GerenciamentoDeAluno.exiteAluno(matricula)) {
            System.out.println("Adiciona aluno");
        }
    }
}
