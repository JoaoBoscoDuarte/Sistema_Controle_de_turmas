package model.faculdade;

import model.disciplina.Disciplina;
import model.exceptions.*;
import model.pessoa.Professor;
import model.servicos.*;

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
        this.gerenciamentoDeArquivos = new GerenciamentoDeArquivos();
        this.gerenciamentoDeDisciplinas = new GerenciamentoDeDisciplinas(gerenciamentoDeProfessores);
        this.gerenciamentoDeTurmas = new GerenciamentoDeTurmas(gerenciamentoDeAlunos, gerenciamentoDeProfessores, gerenciamentoDeDisciplinas, gerenciamentoDeArquivos);
    }

    // Gerenciamento de turmas --------------------------------------------------->
    public void criarTurma(String nome, String matricula) throws ProfessorNaoEncontradoException, DisciplinaNaoEncontradaException, DisciplinaInvalidaException {
        gerenciamentoDeTurmas.criarTurma(nome, matricula);
    }

    public void adicionarAlunoATurma(String matricula, String codigo) throws TurmaInvalidaException, AlunoNaoEncontradoException {
        gerenciamentoDeTurmas.adicionarAlunoATurma(matricula, codigo);
    }

    public void atribuirUnidades(int unidades, String codigo) throws TurmaInvalidaException, IntervaloDeUnidadeException {
        gerenciamentoDeTurmas.atribuirUnidades(unidades, codigo);
    }

    public void cadastrarNotasUnidade (String codigo, int unidade, Double nota, String matricula) throws AlunoNaoEncontradoException, TurmaInvalidaException, IntervaloDeUnidadeException, IntervaloDeNotaException {
        gerenciamentoDeTurmas.cadastrarNotasUnidade(codigo, unidade, nota, matricula);
    }

    public void removerAluno(String matricula) throws AlunoNaoEncontradoException {
        gerenciamentoDeTurmas.removerAluno(matricula);
    }

    public StringBuilder listarTurmas() {
        return gerenciamentoDeTurmas.listarTurmas();
    }

    public double calcularMedia(String matricula, String codigo) throws AlunoNaoEncontradoException, TipoDeMediaNaoDefinidaException, TurmaInvalidaException {
        return gerenciamentoDeTurmas.calcularMedia(matricula, codigo);
    }

    public String verificarAprovacao(double media) {
        return gerenciamentoDeTurmas.verificarAprovacao(media);
    }

    // Gerenciamento de Alunos --------------------------------------------------->
    public void adicionarAluno(String nome, String telefone, String email, String curso) throws PessoaInvalidaException {
        gerenciamentoDeAlunos.adicionaAluno(nome, telefone, email, curso);
    }

    public String listarAlunos() {
        return gerenciamentoDeAlunos.listarAlunos();
    }

    public String consultarDadosAluno(String matricula) throws AlunoNaoEncontradoException {
        return gerenciamentoDeAlunos.consultaDadosAluno(matricula);
    }

    public void desativaAluno(String matricula) throws AlunoNaoEncontradoException {
        gerenciamentoDeAlunos.desativaAluno(matricula);
    }

    public String listarAlunosDaFaculdade() {
        return gerenciamentoDeAlunos.listarAlunosDaFaculdade();
    }

    public boolean existeAluno(String matricula) {
        return gerenciamentoDeAlunos.existeAluno(matricula);
    }

    // Gerenciamento de disciplinas ---------------------------------------------->
    public void cadastrarDisciplina(String nomeDisciplina, int cargaHoraria) throws DisciplinaJaCadastradaException, CargaHorariaInvalidaException, DisciplinaInvalidaException {
        gerenciamentoDeDisciplinas.cadastraDisciplina(nomeDisciplina, cargaHoraria);
    }

    public StringBuilder listarDisciplinas() {
        return gerenciamentoDeDisciplinas.listaDisciplinas();
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

    // Gerenciamento de Professores ---------------------------------------------->
    public void adicionarProfessor(String nome, String telefone, String email) throws Exception {
        gerenciamentoDeProfessores.adicionarProfessor(nome, telefone, email);
    }

    public void adicionarProfessor(String nome, String telefone, String email, List<Disciplina> disciplinasDoProfessor) throws Exception {
        gerenciamentoDeProfessores.adicionarProfessor(nome, telefone, email, disciplinasDoProfessor);
    }

    public StringBuilder listarProfessores() {
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

    public void gerarRelatorioDaTurma(GerenciamentoDeTurmas turmas, String codigo) throws IOException {
        gerenciamentoDeArquivos.gerarRelatorioTurmaTxt(turmas, codigo);
    }
}