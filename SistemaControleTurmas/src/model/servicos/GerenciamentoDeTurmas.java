package model.servicos;

import model.exceptions.*;
import model.pessoa.Aluno;
import model.pessoa.Professor;
import model.turma.Nota;
import model.turma.Turma;
import model.turma.media.TiposDeMediaIF;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GerenciamentoDeTurmas implements Serializable {

    private List<Turma> turmas;
    private GerenciamentoDeAlunos aluno;
    private GerenciamentoDeProfessores professor;
    private GerenciamentoDeDisciplinas disciplina;
    private GerenciamentoDeArquivos arquivos;
    private boolean ativo;

    public GerenciamentoDeTurmas(GerenciamentoDeAlunos aluno, GerenciamentoDeProfessores professor, GerenciamentoDeDisciplinas disciplina, GerenciamentoDeArquivos arquivos) {
        this.turmas = new ArrayList<>();
        this.aluno = aluno;
        this.professor = professor;
        this.disciplina = disciplina;
        this.arquivos = arquivos;
        this.ativo = true;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
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

        //Adiciona a disciplina na lista do professor (caso ele não tenha a disciplina cadastrada antes
        Professor professorDaTurma = professor.buscaProfessor(matricula);
        if (!professorDaTurma.getDisciplinasMinistradas().contains(disciplina)) {
            professorDaTurma.getDisciplinasMinistradas().add(disciplina.buscaDisciplina(nome));
        }
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
    public void cadastrarNotasUnidade(String codigo, int unidade, Double nota) throws IntervaloDeNotaException, IntervaloDeUnidadeException, TurmaInvalidaException, AlunoNaoEncontradoException {
        // Encontra a turma
        Turma turma = buscarTurma(codigo);

        // Verifica se a nota é válida
        if (nota < 0 || nota > 10) {
            throw new IntervaloDeNotaException("Nota deve estar entre 0 e 10.");
        }

        // Verifica se a unidade é válida
        if (unidade < 1 || unidade > (turma.getNumeroUnidades())) {
            throw new IntervaloDeUnidadeException("Unidade inválida. Deve estar entre 1 e " + turma.getNumeroUnidades());
        }

        for (Nota n : turma.getNotasAluno()) {
            n.getNotas().set(unidade, nota);
        }
    }

    // Método para remover aluno pela matrícula
    public void removerAluno(String matricula) throws AlunoNaoEncontradoException {
        for (Turma turma : turmas) {
            boolean removido = turma.getNotasAluno().removeIf(n -> n.getMatricula().equals(matricula));

            if (removido) {
                return;
            }
        }
        throw new AlunoNaoEncontradoException("Aluno não encontrado em nenhuma turma.");
    }

    // Método para listar todas as turmas -----------------------------------> OK
    public String listarTurmas() throws TurmaInvalidaException {
        if (turmas.isEmpty()) {
            throw new TurmaInvalidaException("Nenhuma turma cadastrada.");
        }

        StringBuilder exibir = new StringBuilder();
        for (Turma t : turmas) {
            exibir.append(t).append("\n");
        }

        return exibir.toString();
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

    // Método para listar os alunos de uma turma -----------------------------> OK
    public String listaAlunoDeTurma(String codigo) throws TurmaInvalidaException {
        Turma t = buscarTurma(codigo);
        StringBuilder listar = new StringBuilder();
        for (Aluno a: t.getAlunos()){
            listar.append(a).append("\n");
        }
        return listar.toString();
    }

    // Método para verificar aprovação --------------------------------------> OK
    public String verificarAprovacao(double media) {
        return media >= 7 ? "APROVADO" : "REPROVADO";
    }

    // Método para calcular a média dos alunos -----------------------------> OK
    public double calculaMedia(List<Double> notas, String codigo) throws TurmaInvalidaException {
        Turma turma = buscarTurma(codigo);
        return turma.getTipoDeMedia().calcularMedia(notas);
    }

    //Método para condifgurar turma (definir tipo de media e quantidade de unidades avaliativas)
    public void configurarTurma(String codigo, int qtdUnidadesAvaliativas, TiposDeMediaIF tiposDeMediaIF) throws TurmaInvalidaException, IntervaloDeUnidadeException {
        Turma turma = buscarTurma(codigo);

        if (qtdUnidadesAvaliativas < 2) {
            throw new IntervaloDeUnidadeException("Quantidade de unidades avaliativas não pode ser menor que 2");
        }

        if (turma.isUnidadesAvalaitivasMudada()) {
            turma.setTipoDeMedia(tiposDeMediaIF);
            System.out.println("A quantidade de unidades avaliativas já foi definida. Apenas tipo de média foi atualizado");

        } else {
            turma.setTipoDeMedia(tiposDeMediaIF);
            turma.setNumeroUnidades(qtdUnidadesAvaliativas);
            System.out.println("Quantidade de unidades avaliativas e tipo de média definidos.");
            turma.setUnidadesAvalaitivasMudada(true);
        }
    }

    // Método para exibir em tela o relatorio final das notas dos alunos de uma turma
    public String exibirRelatorioFinalemTela(String codigo) throws TurmaInvalidaException, AlunoNaoEncontradoException {
        Turma turma = buscarTurma(codigo);

        if (turma == null) {
            throw new TurmaInvalidaException("Turma não encontrada");
        }

        String exibir = "";
        for (Nota n : turma.getNotasAluno()) {
            exibir += "|Nome: " + aluno.retornaNomeAluno(n.getMatricula()) + "\n" +
                    "|Notas: " + n.getNotas() + "\n" +
                    "|Media: " + calculaMedia(n.getNotas(), codigo) + "\n" +
                    "|Situação: " + verificarAprovacao(calculaMedia(n.getNotas(), codigo));
        }

        return exibir;
    }

    public void encerrarTurma(String matricula, String codigo) throws TurmaInvalidaException, ProfessorNaoEncontradoException, IntervaloDeNotaException {
        Turma turma = buscarTurma(codigo);
        if (turma == null) {
            throw new TurmaInvalidaException("Turma não encontrada.");
        }

        Professor professorDaTurma = turma.getProfessor();
        if (!professorDaTurma.getMatricula().equals(matricula)) {
            throw new ProfessorNaoEncontradoException("A turma não pertence a este professor.");
        }

        for (Nota nota : turma.getNotasAluno()) {
            for (Double n : nota.getNotas()) {
                if (n == null) {
                    throw new IntervaloDeNotaException("Ainda existem notas não cadastradas para esta turma.");
                }
            }
        }
        turma.setAtivo(false);
    }

    public List<Turma> getTurmas() {
        return turmas;
    }
}