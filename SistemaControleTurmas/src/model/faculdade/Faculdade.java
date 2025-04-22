package model.faculdade;

import model.disciplina.Disciplina;
import model.exceptions.*;
import model.pessoa.Professor;
import model.servicos.*;

import java.io.IOException;


public class Faculdade {
    GerenciamentoDeAlunos gerenciamentoDeAlunos;
    GerenciamentoDeProfessores gerenciamentoDeProfessores;
    GerenciamentoDeArquivos gerenciamentoDeArquivos;
    GerenciamentoDeDisciplinas gerenciamentoDeDisciplinas;
    GerenciamentoDeTurmas gerenciamentoDeTurmas;


    public Faculdade() {
        this.gerenciamentoDeAlunos = new GerenciamentoDeAlunos();
        this.gerenciamentoDeProfessores = new GerenciamentoDeProfessores();
        this.gerenciamentoDeArquivos = new GerenciamentoDeArquivos();
        this.gerenciamentoDeDisciplinas = new GerenciamentoDeDisciplinas(gerenciamentoDeProfessores);
        this.gerenciamentoDeTurmas = new GerenciamentoDeTurmas(gerenciamentoDeAlunos, gerenciamentoDeProfessores, gerenciamentoDeDisciplinas, gerenciamentoDeArquivos);
    }

    // Gerenciamento de turmas --------------------------------------------------->
    public void criarTurma(String nome, String matricula) throws ProfessorNaoEncontradoException, DisciplinaNaoEncontradaException {
        gerenciamentoDeTurmas.criarTurma(nome, matricula);
    }

    public void adicionarAlunoATurma(String matricula, String codigo) {
        gerenciamentoDeTurmas.adicionarAlunoATurma(matricula, codigo);
    }

    public void atribuirUnidades(int unidades, String codigo) {
        gerenciamentoDeTurmas.atribuirUnidades(unidades, codigo);
    }

    public void cadastrarNotasUnidade (String codigo, int unidade, Double nota, String matricula) throws AlunoNaoEncontradoException {
        gerenciamentoDeTurmas.cadastrarNotasUnidade(codigo, unidade, nota, matricula);
    }

    public void removerAluno(String matricula) throws AlunoNaoEncontradoException {
        gerenciamentoDeTurmas.removerAluno(matricula);
    }

    public void gerarRelatorioDaTurma() throws IOException {
        gerenciamentoDeTurmas.gerarRelatorioDaTurma();
    }

    public StringBuilder listarTurmas() {
        return gerenciamentoDeTurmas.listarTurmas();
    }

    // Gerenciamento de Alunos --------------------------------------------------->
    public void adicionarAluno(String nome, String telefone, String email, String curso) throws Exception {
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

    // Gerenciamento de disciplinas ---------------------------------------------->
    public void cadastrarDisciplina(String nomeDisciplina, int cargaHoraria) throws DisciplinaJaCadastradaException {
        gerenciamentoDeDisciplinas.cadastraDisciplina(nomeDisciplina, cargaHoraria);
    }

    public StringBuilder listarDisciplinas() {
        return gerenciamentoDeDisciplinas.listaDisciplinas();
    }

    public void associarProfessorADisciplina(String nome, String matricula) throws AssociacaoInvalidaException, ProfessorNaoEncontradoException, DisciplinaNaoEncontradaException {
        gerenciamentoDeDisciplinas.associarProfessorADisciplina(nome, matricula);
    }

    public Disciplina buscaDisciplina(String nomeDisciplina) throws DisciplinaNaoEncontradaException {
        return gerenciamentoDeDisciplinas.buscaDisciplina(nomeDisciplina);
    }

    // Gerenciamento de Professores ---------------------------------------------->
    public void adicionarProfessor(String nome, String telefone, String email) throws Exception {
        gerenciamentoDeProfessores.adicionarProfessor(nome, telefone, email);
    }

    public StringBuilder listarProfessores() {
        return gerenciamentoDeProfessores.listarProfessores();
    }

    public Professor buscaProfessor(String matricula) throws ProfessorNaoEncontradoException {
        return gerenciamentoDeProfessores.buscaProfessor(matricula);
    }
}