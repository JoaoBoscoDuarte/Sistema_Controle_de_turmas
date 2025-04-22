package model.faculdade;

import model.disciplina.Disciplina;
import model.exceptions.*;
import model.pessoa.Professor;
import model.servicos.*;


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
    public void criarTurma(Disciplina disciplina, String matriculaDoProfessor) throws ProfessorNaoEncontradoException {
        gerenciamentoDeTurmas.criarTurma(disciplina, matriculaDoProfessor);
    }

    public void adicionarAlunoATurma(String matricula, String codigoDaTurma) {
        gerenciamentoDeTurmas.adicionarAlunoATurma(matricula, codigoDaTurma);
    }

    public void adicionarProfessorATurma(String matricula, String codigoDaTurama) {
        gerenciamentoDeTurmas.adicionarAlunoATurma(matricula, codigoDaTurama);
    }

    public void atribuirQuantidadeDeUnidadesAvaliativas(int qtdUnidadesAvaliativas, String codigoDaTurma) {
        gerenciamentoDeTurmas.atribuirQuantidadeDeUnidadesAvaliativas(qtdUnidadesAvaliativas, codigoDaTurma);
    }

    public void cadastrarNotasUnidade (String codigoDaTurma, int unidade, Double nota, String matricula) {
        gerenciamentoDeTurmas.cadastrarNotasUnidade(codigoDaTurma, unidade, nota, matricula);
    }

    public void removerAluno(String matricula) throws AlunoNaoEncontradoException {
        gerenciamentoDeTurmas.removerAluno(matricula);
    }

    public String gerarRelatorioDaTurma() {
        return gerenciamentoDeTurmas.gerarRelatorioDaTurma();
    }

    public String listarTurmas() {
        return gerenciamentoDeTurmas.listarTurmas();
    }

    // Gerenciamento de Alunos --------------------------------------------------->
    public void adicionarAluno(String nome, String telefone, String email, String curso) throws Exception {
        gerenciamentoDeAlunos.adicionaAluno(nome, telefone, email, curso);
    }

    public String listarAlunos() {
        return gerenciamentoDeAlunos.listaAlunos();
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

    public String listarDisciplinas() {
        return gerenciamentoDeDisciplinas.listaDisciplinas();
    }

    public void associarProfessor(Disciplina disciplina, String matriculaDoProfessor) throws AssociacaoInvalidaException, ProfessorNaoEncontradoException, DisciplinaNaoEncontradaException {
        gerenciamentoDeDisciplinas.associarProfessor(disciplina, matriculaDoProfessor);
    }

    public Disciplina procurarDisciplina(String nomeDisciplina) throws DisciplinaNaoEncontradaException {
        return gerenciamentoDeDisciplinas.procuraDisciplina(nomeDisciplina);
    }

    // Gerenciamento de Professores ---------------------------------------------->
    public void adicionarProfessor(String nome, String telefone, String email) throws Exception {
        gerenciamentoDeProfessores.adicionarProfessor(nome, telefone, email);
    }

    public String listarProfessores() {
        return  gerenciamentoDeProfessores.listarProfessores();
    }

    public Professor buscaProfessor(String matricula) throws ProfessorNaoEncontradoException {
        return gerenciamentoDeProfessores.buscaProfessor(matricula);
    }
}