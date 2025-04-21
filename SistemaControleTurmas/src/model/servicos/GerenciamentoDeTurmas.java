package model.servicos;

import model.exceptions.*;
import model.pessoa.Aluno;
import model.pessoa.Professor;
import model.turma.Turma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerenciamentoDeTurmas {
    // A lista turma contem todas as turmas, a lista professoresAssociados contem todos os professores
    // associados as turmas de modo que, a associação corresponde ao índice de ambas as listas

    private List<Turma> turmas;
    private List<Professor> professoresAssociados;

    private GerenciamentoDeAlunos aluno;
    private GerenciamentoDeProfessores professor;
    private GerenciamentoDeDisciplinas disciplina;

    public GerenciamentoDeTurmas() {
        this.turmas = new ArrayList<>();
        this.professoresAssociados = new ArrayList<>();
    }

    // Cria turma
    public void criarTurma(String nomeDisciplina, String matricula) throws ProfessorNaoEncontradoException, DisciplinaNaoEncontradaException {
        if (!disciplina.existeDisciplina(nomeDisciplina)) {
            throw new DisciplinaNaoEncontradaException("Disciplina não existe.");
        }

        if (!professor.existeProfessor(matricula)) {
            throw new ProfessorNaoEncontradoException("Professor não existe.");
        }

        turmas.add(new Turma(disciplina.procuraDisciplina(nomeDisciplina), professor.buscaProfessor(matricula)));
    }

    // Adiciona aluno pela matrícula
    public void adicionarAlunoATurma(String matricula, String codigo) {
        Turma turma = exiteTurma(codigo);
        if (aluno.existeAluno(matricula) && turma != null) {
            turma.getMatriculasProfessores().add(matricula);
        }
    }

    // Adicionar professor pela matrícula
    public void adicionarProfessorATurma(String matricula, String codigo) throws ProfessorNaoEncontradoException {
        Turma turma = exiteTurma(codigo);
        if (professor.existeProfessor(matricula) && turma != null) {
            turma.getMatriculasProfessores().add(matricula);
        }
    }

    // Atribuir quantidade de unidades avaliativas
    public void atribuirUnidades(int unidades, String codigo) throws IntervaloDeUnidadeException {
        if (1 < unidades) {
            Turma turma = exiteTurma(codigo);
            turma.setNumeroUnidades(unidades);
        }
    }

    // Cadastra notas as unidades
    public void cadastrarNotasUnidade(String codigoTurma, int unidade, Double nota, String matriculaAluno) throws IntervaloDeNotaException, IntervaloDeUnidadeException, TurmaNaoEncontradaException, AlunoNaoEncontradoException {

        if (nota < 0 || nota > 10) {
            throw new IntervaloDeNotaException("Nota deve estar entre 0 e 10");
        }

        // Encontra a turma
        Turma turma = exiteTurma(codigoTurma);
        if (turma == null) {
            throw new TurmaNaoEncontradaException("Turma não encontrada com o código: " + codigoTurma);
        }

        // Verifica se o aluno está na turma
        if (!turma.getMatriculasAlunos().contains(matriculaAluno)) {
            throw new AlunoNaoEncontradoException("Aluno não está matriculado nesta turma");
        }

        // Verifica se a unidade é válida
        if (unidade < 1 || unidade > turma.getNumeroUnidades()) {
            throw new IntervaloDeUnidadeException("Unidade inválida. Deve estar entre 1 e " + turma.getNumeroUnidades());
        }

        // Obtém ou cria o mapa de notas do aluno na turma
        Map<String, List<Double>> notasDaTurma = turma.getNotasAlunos();
        if (notasDaTurma == null) {
            notasDaTurma = new HashMap<>();
            turma.setNotasAlunos(notasDaTurma);
        }

        // Obtém ou inicializa a lista de notas do aluno
        List<Double> notasDoAluno = notasDaTurma.get(matriculaAluno);
        if (notasDoAluno == null) {
            notasDoAluno = new ArrayList<>(turma.getNumeroUnidades());
            for (int i = 0; i < turma.getNumeroUnidades(); i++) {
                notasDoAluno.add(null); // Inicializa com null para todas as unidades
            }
            notasDaTurma.put(matriculaAluno, notasDoAluno);
        }

        // Adiciona a nota na posição correta (unidade-1 porque lista começa em 0)
        notasDoAluno.set(unidade - 1, nota);
    }

    // Remove aluno pela matrícula
    // remove aluno da lista do gerenciador de alunos e da lista de notas por unidade dos alunos
    // e matriculas dos alunos
    public void removerAluno(String matricula) throws AlunoNaoEncontradoException {

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

    // Método que retorna a turma existente
    public Turma exiteTurma(String codigo) {
        for (Turma t : turmas) {
            if (t.getCodigoTurma().equals(codigo)) {
                return t;
            }
        }

        return null;
    }
}