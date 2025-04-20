package model.servicos;

import model.disciplina.Disciplina;
import model.exceptions.AlunoNaoEncontradoException;
import model.exceptions.ProfessorNaoEncontradoException;
import model.pessoa.Professor;
import model.turma.Nota;
import model.turma.Turma;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GerenciamentoDeTurmas {
    // A lista turma contem todas as turmas, a lista professoresAssociados contem todos os professores
    // associados as turmas de modo que, a associação corresponde ao índice de ambas as listas

    private List<Turma> turmas;
    private List<Professor> professoresAssociados;

    private GerenciamentoDeAlunos gerenciamentoDeAlunos;
    private GerenciamentoDeProfessores gerenciamentoDeProfessores;
    private GerenciamentoDeDisciplinas gerenciadorDeDisciplinas;

    public GerenciamentoDeTurmas() {
        this.turmas = new ArrayList<>();
        this.professoresAssociados = new ArrayList<>();
    }

    // Cria turma
    public void criarTurma(Disciplina disciplina, String matricula) throws ProfessorNaoEncontradoException {
        if (gerenciadorDeDisciplinas.existeDisciplina(disciplina) && gerenciamentoDeProfessores.existeProfessor(matricula)) {
            Professor professor = gerenciamentoDeProfessores.buscaProfessor(matricula);
            turmas.add(new Turma(disciplina, professor));
            professoresAssociados.add(professor);
        }
    }

    // Adiciona aluno pela matrícula
    public void adicionarAlunoATurma(String matricula, String codigoDaTurma) {
        for (Turma t : turmas) {
            if (t.getCodigoTurma().equals(codigoDaTurma) && !(t.getMatriculasAlunos().contains(matricula))) {
                t.getMatriculasAlunos().add(matricula);
            }
        }
    }

    // Adicionar professor pela matrícula
    public void adicionarProfessorATurma(String matricula, String codigoDaTurma) {
        for (Turma t : turmas) {
            if (t.getCodigoTurma().equals(codigoDaTurma) && !t.getMatriculasProfessores().contains(matricula)) {
                t.getMatriculasAlunos().add(matricula);
            }
        }
    }

    // Atribuir quantidade de unidades avaliativas
    public void atribuirQuantidadeDeUnidadesAvaliativas(int qtdUnidadesAvaliativas, String codigoDaTurma) {
        if (qtdUnidadesAvaliativas > 1) {
            for (Turma t : turmas) {
                if (t.getCodigoTurma().equals(codigoDaTurma)) {
                    t.setQtdUnidadesAvaliativas(qtdUnidadesAvaliativas);
                }
            }
        }
    }

    // Cadastrar notas doas alunos
    public void cadastrarNotasUnidade(String codigoDaTurma, int unidade, Double nota, String matricula) {
        int aluno = 0;
        for (Turma t : turmas) {
            if (t.getCodigoTurma().equals(codigoDaTurma)) {
                for (Nota n : t.getNotasAluno()) {
                    if (n.getMatriculaAluno().equals(matricula)) {
                        n.adicionarNota(unidade, nota);
                    }
                }
            }
        }
    }

    // Remove aluno pela matrícula
    // remove aluno da lista do gerenciador de alunos e da lista de notas por unidade dos alunos
    // e matriculas dos alunos
    public void removerAluno(String matricula) throws AlunoNaoEncontradoException {
        gerenciamentoDeAlunos.desativaAluno(matricula);

        for (Turma t : turmas) {
            if (t.getMatriculasAlunos().equals(matricula)) {
                t.getMatriculasAlunos().remove(t);
                t.getNotasAluno().remove(t);
            }
        }
    }

    // Gerar o relatório da turma
    public String gerarRelatorioDaTurma() {
        return "FAZER ESSE RELATORIO MIZERAAAAAA";
    }

    // Listar todas as turmas
    public String listarTurmas() {
        String exibir = "";

        for (Turma t : turmas) {
            exibir += t + "\n";
        }

        return exibir;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }
}