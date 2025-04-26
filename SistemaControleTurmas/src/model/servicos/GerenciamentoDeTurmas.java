package model.servicos;

import model.exceptions.*;
import model.turma.Nota;
import model.turma.Turma;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GerenciamentoDeTurmas {
    private List<Turma> turmas;
    private GerenciamentoDeAlunos aluno;
    private GerenciamentoDeProfessores professor;
    private GerenciamentoDeDisciplinas disciplina;
    private GerenciamentoDeArquivos arquivos;

    public GerenciamentoDeTurmas(GerenciamentoDeAlunos aluno, GerenciamentoDeProfessores professor, GerenciamentoDeDisciplinas disciplina, GerenciamentoDeArquivos arquivos) {
        this.turmas = new ArrayList<>();
        this.aluno = aluno;
        this.professor = professor;
        this.disciplina = disciplina;
        this.arquivos = arquivos;
    }

    // Método par cria turma ------------------------------------------------------------> OK
    public void criarTurma(String nome, String matricula) throws ProfessorNaoEncontradoException, DisciplinaNaoEncontradaException, DisciplinaInvalidaException {
        if (!disciplina.existeDisciplina(nome)) {
            throw new DisciplinaNaoEncontradaException("Disciplina não existe.");
        }

        if (!professor.existeProfessor(matricula)) {
            throw new ProfessorNaoEncontradoException("Professor não existe.");
        }

        turmas.add(new Turma(disciplina.buscaDisciplina(nome), professor.buscaProfessor(matricula)));
    }

    // Método para adicionar aluno a turma ----------------------------------------------> Ok
    public void adicionarAlunoATurma(String matricula, String codigo) throws TurmaInvalidaException, AlunoNaoEncontradoException {
        excecoesTurma(codigo);
        Turma turma = buscarTurma(codigo);

        // Adiciona o aluno ao mapa e inicializa a lista das notas dele
        if (aluno.getListaAlunos().contains(aluno.buscaAluno(matricula))) {
            Nota nota = new Nota(matricula, turma.getNumeroUnidades());
            turma.getNotasAluno().add(nota);
        }
    }

    // Método para atribuir quantidade de unidades avaliativas --------------------------> OK
    public void atribuirUnidades(int unidades, String codigo) throws IntervaloDeUnidadeException, TurmaInvalidaException {
        Turma turma = buscarTurma(codigo);
        if (1 < unidades) {
            turma.setNumeroUnidades(unidades);

        } else {
            throw new IntervaloDeUnidadeException("Número de unidades inválido.");
        }
    }

    // Método para cadastra notas as unidades
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
            if (n.getMatricula().equals(matricula)) {
                List<Double> notas = n.getNotas();

                // Garante que a lista tenha espaço suficiente para a unidade desejada
                while (notas.size() <= unidade) {
                    notas.add(0.0); // valor padrão
                }

                // Atualiza a nota na unidade específica (observe o decremento no índice para pegar a posição certa)
                notas.set(unidade - 1, nota);
            }
        }
    }

    // Método para remover aluno pela matrícula
    public void removerAluno(String matricula) throws AlunoNaoEncontradoException {
        for (Turma turma : turmas) {
            boolean removido = turma.getNotasAluno().removeIf(n -> n.getMatricula().equals(matricula));
            aluno.desativaAluno(matricula);
            if (removido) return;
        }
        throw new AlunoNaoEncontradoException("Aluno não encontrado em nenhuma turma.");
    }

    // Método para gerar o relatório da turma
    public void gerarRelatorioDaTurma() throws IOException {
        arquivos.gerarRelatorioTxt();
    }

    // Método para listar todas as turmas -----------------------------------> OK
    public StringBuilder listarTurmas() {
        if (turmas.isEmpty()) {
            return new StringBuilder("Nenhuma turma cadastrada.");
        }

        StringBuilder exibir =  new StringBuilder();
        for (Turma t : turmas) {
            exibir.append(t).append("\n");
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

    // Método para verificar aprovação --------------------------------------> OK
    public String verificarAprovacao(double media) {
        return media >= 7 ? "APROVADO" : "REPROVADO";
    }

    // Método para calcular a média de um aluno -----------------------------> OK
    public double calcularMedia(String matricula, String codigo) throws AlunoNaoEncontradoException, TipoDeMediaNaoDefinidaException, TurmaInvalidaException {
        Turma turma = buscarTurma(codigo);

        for (Nota n : turma.getNotasAluno()) {
            if (n.getMatricula().equals(matricula)) {
                if (turma.getTipoDeMedia() == null) {
                    throw new TipoDeMediaNaoDefinidaException("Tipo de média não foi definido.");
                }
                return turma.getTipoDeMedia().calcularMedia(n.getNotas());

            }
        }

        throw new AlunoNaoEncontradoException("Aluno não encontrado na turma.");
    }

    public List<Turma> getTurmas() {
        return turmas;
    }
}