package model.servicos;

import model.exceptions.*;
import model.pessoa.Professor;
import model.turma.Nota;
import model.turma.Turma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerenciamentoDeTurmas {
    private List<Turma> turmas;
    private GerenciamentoDeAlunos aluno;
    private GerenciamentoDeProfessores professor;
    private GerenciamentoDeDisciplinas disciplina;

    public GerenciamentoDeTurmas() {
        this.turmas = new ArrayList<>();
    }

    // Método par cria turma ------------------------------------------------------------> OK
    public void criarTurma(String nome, String matricula) throws ProfessorNaoEncontradoException, DisciplinaNaoEncontradaException {
        if (!disciplina.existeDisciplina(nome)) {
            throw new DisciplinaNaoEncontradaException("Disciplina não existe.");
        }

        if (!professor.existeProfessor(matricula)) {
            throw new ProfessorNaoEncontradoException("Professor não existe.");
        }

        turmas.add(new Turma(disciplina.buscaDisciplina(nome), professor.buscaProfessor(matricula)));
    }

    // Método para adicionar aluno a turma ----------------------------------------------> Ok
    public void adicionarAlunoATurma(String matricula, String codigo) {
        excecoesTurma(codigo);
        Turma turma = buscarTurma(codigo);

        // Adiciona o aluno ao mapa e inicializa a lista das notas dele
        if (aluno.getListaAlunos().contains(aluno.buscaAluno(matricula))) {
            Nota nota = new Nota(turma.getNumeroUnidades());
            Map<String, List<Double>> notasAluno = nota.getNotasDoAluno();

            notasAluno.put(matricula, new ArrayList<>());
        }
    }

    // Método para atribuir quantidade de unidades avaliativas --------------------------> OK
    public void atribuirUnidades(int unidades, String codigo) throws IntervaloDeUnidadeException {
        Turma turma = buscarTurma(codigo);
        if (1 < unidades) {
            turma.setNumeroUnidades(unidades);

        } else {
            throw new IntervaloDeUnidadeException("Número de unidades inválido.");
        }
    }

    // Cadastra notas as unidades
    public void cadastrarNotasUnidade(String codigo, int unidade, Double nota, String matricula) throws IntervaloDeNotaException, IntervaloDeUnidadeException, TurmaInvalidaException, AlunoNaoEncontradoException {
        // Encontra a turma
        Turma turma = buscarTurma(codigo);

        // Verifica se a nota é válida
        if (nota < 0 || nota > 10) {
            throw new IntervaloDeNotaException("Nota deve estar entre 0 e 10");
        }

        // Verifica se a unidade é válida
        if (unidade < 1 || unidade > (turma.getNumeroUnidades())) {
            throw new IntervaloDeUnidadeException("Unidade inválida. Deve estar entre 1 e " + turma.getNumeroUnidades());
        }

        for (Nota n : turma.getNotasAluno()) {
            if (n.getNotasDoAluno().containsKey(matricula)) {
                List<Double> notas = n.getNotasDoAluno().get(matricula);

                // Garante que a lista tenha espaço suficiente para a unidade desejada
                while (notas.size() <= turma.getNumeroUnidades()) {
                    notas.add(0.0); // valor padrão
                }

                // Atualiza a nota na unidade específica
                notas.set(unidade, nota);
            }
        }
    }

    // Método para remover aluno pela matrícula
    public void removerAluno(String matricula) throws AlunoNaoEncontradoException {

    }

    // Gerar o relatório da turma
    public String gerarRelatorioDaTurma() {
        return "FAZER ESSE RELATORIO MIZERAAAAAA";
    }

    // Método para listar todas as turmas -----------------------------------> OK
    public StringBuilder listarTurmas() {
        StringBuilder exibir =  new StringBuilder();

        for (Turma t : turmas) {
            exibir.append(t + "\n");
        }

        return exibir;
    }

    // Método que condensa as exceções de turma -----------------------------> OK
    public void excecoesTurma(String codigo) throws TurmaInvalidaException {
        // Verifica se a turma é nula
        if (codigo == null) {
            throw new TurmaInvalidaException("Turma inválida. O campo de código não pode ser nulo.");
        }

        // Verifica se a turma exite
        if (!turmas.contains(buscarTurma(codigo))) {
            throw new TurmaInvalidaException("A turma não existe.");
        }
    }

    // método para buscar turma (retorna a turma se achar) ------------------> OK
    public Turma buscarTurma(String codigo) throws TurmaInvalidaException {
        if (codigo == null) {
            throw new TurmaInvalidaException("Turma inválida. O campo de código não pode ser nulo.");
        }

        for (Turma t : turmas) {
            if (t.getCodigoTurma().equals(codigo)) {
                return t;
            }
        }

        throw new TurmaInvalidaException("A turma não existe.");
    }
}