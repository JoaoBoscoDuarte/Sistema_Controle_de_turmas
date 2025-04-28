package model.faculdade;

import model.disciplina.Disciplina;
import model.exceptions.*;
import model.pessoa.Professor;
import model.servicos.*;
import model.turma.Turma;
import model.turma.media.TiposDeMediaIF;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;


public class Faculdade implements Serializable {
    private static final long serialVersionUID = 1L;

    private GerenciamentoDeAlunos gerenciamentoDeAlunos;
    private GerenciamentoDeProfessores gerenciamentoDeProfessores;
    private GerenciamentoDeArquivos gerenciamentoDeArquivos;
    private GerenciamentoDeDisciplinas gerenciamentoDeDisciplinas;
    private GerenciamentoDeTurmas gerenciamentoDeTurmas;


    public Faculdade() {
        this.gerenciamentoDeAlunos = new GerenciamentoDeAlunos();
        this.gerenciamentoDeProfessores = new GerenciamentoDeProfessores();
        this.gerenciamentoDeDisciplinas = new GerenciamentoDeDisciplinas(gerenciamentoDeProfessores);
        this.gerenciamentoDeTurmas = new GerenciamentoDeTurmas(gerenciamentoDeAlunos, gerenciamentoDeProfessores, gerenciamentoDeDisciplinas, gerenciamentoDeArquivos);
        this.gerenciamentoDeArquivos = new GerenciamentoDeArquivos(gerenciamentoDeTurmas, gerenciamentoDeAlunos);
    }

    // Gerenciamento de turmas ---------------------------------------------------> ok
    public void criarTurma(String nome, String matricula) throws ProfessorNaoEncontradoException, DisciplinaNaoEncontradaException, DisciplinaInvalidaException {
        gerenciamentoDeTurmas.criarTurma(nome, matricula);
    }

    public void adicionarAlunoATurma(String matricula, String codigo) throws TurmaInvalidaException, AlunoNaoEncontradoException {
        gerenciamentoDeTurmas.adicionarAlunoATurma(matricula, codigo);
    }

    public void atribuirUnidades(int unidades, String codigo) throws TurmaInvalidaException, IntervaloDeUnidadeException {
        gerenciamentoDeTurmas.atribuirUnidades(unidades, codigo);
    }

    public void cadastrarNotasUnidade (String codigo, int unidade, Double nota) throws AlunoNaoEncontradoException, TurmaInvalidaException, IntervaloDeUnidadeException, IntervaloDeNotaException {
        gerenciamentoDeTurmas.cadastrarNotasUnidade(codigo, unidade, nota);
    }

    public void removerAluno(String matricula) throws AlunoNaoEncontradoException {
        gerenciamentoDeTurmas.removerAluno(matricula);
    }

    public double calcularMedia(List<Double> notas, String codigo) throws AlunoNaoEncontradoException, TipoDeMediaNaoDefinidaException, TurmaInvalidaException {
        return gerenciamentoDeTurmas.calculaMedia(notas, codigo);
    }

    public String verificarAprovacao(double media) {
        return gerenciamentoDeTurmas.verificarAprovacao(media);
    }

    public List<Turma> getTurmas() {
        return gerenciamentoDeTurmas.getTurmas();
    }

    public Turma buscarTurma(String codigo) throws TurmaInvalidaException {
        return gerenciamentoDeTurmas.buscarTurma(codigo);
    }

    public String listarAlunosDeTurma(String codigo) throws TurmaInvalidaException {
       return gerenciamentoDeTurmas.listaAlunoDeTurma(codigo);
    }

    public String listarTurmas() throws TurmaInvalidaException {
        return gerenciamentoDeTurmas.listarTurmas();
    }

    public String exibirRelatorioFinalEmTela(String codigo) throws TurmaInvalidaException, AlunoNaoEncontradoException {
        return gerenciamentoDeTurmas.exibirRelatorioFinalemTela(codigo);
    }
  
    public void encerrarTurma(String matricula, String codigo) throws TurmaInvalidaException, ProfessorNaoEncontradoException, IntervaloDeNotaException {
        gerenciamentoDeTurmas.encerrarTurma(matricula, codigo);
    }

    public void configurarTurma(String codigo, int qtdUnidadesAvaliativas, TiposDeMediaIF tiposDeMediaIF) throws IntervaloDeUnidadeException, TurmaInvalidaException {
        gerenciamentoDeTurmas.configurarTurma(codigo, qtdUnidadesAvaliativas, tiposDeMediaIF);
    }

    // Gerenciamento de Alunos ---------------------------------------------------> ok
    public void adicionarAluno(String nome, String telefone, String email, String curso) throws PessoaInvalidaException {
        gerenciamentoDeAlunos.adicionaAluno(nome, telefone, email, curso);
    }

    public String consultarDadosAluno(String matricula) throws AlunoNaoEncontradoException {
        return gerenciamentoDeAlunos.consultaDadosAluno(matricula);
    }

    public void desativaAluno(String matricula) throws AlunoNaoEncontradoException {
        gerenciamentoDeAlunos.desativaAluno(matricula);
    }

    public String listarAlunosDaFaculdade() throws AlunoNaoEncontradoException {
        return gerenciamentoDeAlunos.listarAlunosDaFaculdade();
    }

    public boolean existeAluno(String matricula) {
        return gerenciamentoDeAlunos.existeAluno(matricula);
    }

    // Gerenciamento de disciplinas ----------------------------------------------> ok
    public void cadastrarDisciplina(String nomeDisciplina, String codigo, int cargaHoraria) throws DisciplinaJaCadastradaException, CargaHorariaInvalidaException, DisciplinaInvalidaException, NomeDaDisciplinaInvalidoException {
        gerenciamentoDeDisciplinas.cadastraDisciplina(nomeDisciplina, codigo, cargaHoraria);
    }

    public String listarDisciplinas() throws DisciplinaNaoEncontradaException {
        return gerenciamentoDeDisciplinas.listarDisciplinas();
    }

    public void associarProfessorADisciplina(String nome, String matricula) throws AssociacaoInvalidaException, ProfessorNaoEncontradoException, DisciplinaNaoEncontradaException, DisciplinaInvalidaException {
        gerenciamentoDeDisciplinas.associarProfessorADisciplina(nome, matricula);
    }

    public Disciplina buscaDisciplina(String nomeDisciplina) throws DisciplinaNaoEncontradaException, DisciplinaInvalidaException {
        return gerenciamentoDeDisciplinas.buscaDisciplina(nomeDisciplina);
    }

    public boolean existeDisciplina(String nome) {
        return gerenciamentoDeDisciplinas.existeDisciplina(nome);
    }

    // Gerenciamento de Professores ----------------------------------------------> ok
    public void adicionarProfessor(String nome, String telefone, String email) throws Exception {
        gerenciamentoDeProfessores.adicionarProfessor(nome, telefone, email);
    }

    public void adicionarProfessor(String nome, String telefone, String email, List<Disciplina> disciplinasDoProfessor) throws Exception {
        gerenciamentoDeProfessores.adicionarProfessor(nome, telefone, email, disciplinasDoProfessor);
    }

    public String listarProfessores() throws ProfessorNaoEncontradoException {
        return gerenciamentoDeProfessores.listarProfessores();
    }

    public Professor buscaProfessor(String matricula) throws ProfessorNaoEncontradoException {
        return gerenciamentoDeProfessores.buscaProfessor(matricula);
    }

    // Arquivos ---------------------------------------------------------------> OK
    public Faculdade carregaControleDeTurmas() throws IOException, ClassNotFoundException {
        return gerenciamentoDeArquivos.carregarControleTurmas();
    }

    public void salvaControleDeTurmas() throws IOException {
        gerenciamentoDeArquivos.salvarControleTurmas(this);
    }

    public void gerarRelatorioDaFaculdade() throws IOException {
        gerenciamentoDeArquivos.gerarRelatorioFaculdadeTxt(gerenciamentoDeAlunos, gerenciamentoDeProfessores, gerenciamentoDeDisciplinas, gerenciamentoDeTurmas);
    }

    public void gerarRelatorioDaTurma(String codigo) throws IOException, TurmaInvalidaException {
        gerenciamentoDeArquivos.gerarRelatorioTurmaTxt(codigo);
    }

    public void gerarRelatorioNotaFinalTurma(String codigo) throws TurmaInvalidaException, AlunoNaoEncontradoException, IOException {
        gerenciamentoDeArquivos.gerarRelatorioNotaFinalTurma(codigo);
    }
}