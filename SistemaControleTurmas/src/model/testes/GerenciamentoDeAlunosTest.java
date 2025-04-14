package model.testes;

import model.pessoa.Aluno;
import model.exceptions.AlunoNaoEncontradoException;
import model.servicos.GerenciamentoDeAlunos;
import model.exceptions.PessoaInvalidaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GerenciamentoDeAlunosTest {

    private GerenciamentoDeAlunos gerenciamento;

    @BeforeEach
    void inicializar() {
        gerenciamento = new GerenciamentoDeAlunos();
    }

    @Test
    void adicionaAluno() throws Exception {
        String nome = "Beatriz";
        String telefone = "83444555666";
        String email = "beatriz.vasconcelos@email";

        gerenciamento.adicionaAluno(nome, telefone, email);

        assertTrue(gerenciamento.listaAlunos().contains("Beatriz"));
    }

    @Test
    void testAdicionaAluno() throws PessoaInvalidaException {
        String nome2 = "Anna";
        String telefone2 = "83111222333";
        String email2 = "anna.alves@email";

        Aluno aluno = new Aluno(nome2, telefone2, email2);

        gerenciamento.adicionaAluno(aluno);

        assertTrue(gerenciamento.listaAlunos().contains("Anna"));
    }

    @Test
    void listaAlunos() throws Exception {
        gerenciamento.adicionaAluno("Anna", "83123456789", "bea@email");

        String lista = gerenciamento.listaAlunos();

        assertTrue(lista.contains("Anna"));
        assertTrue(lista.contains("83123456789"));
        assertTrue(lista.contains("bea@email"));

        //assertTrue(lista.contains("Joana"));

    }

    @Test
    void consultaDadosAluno() throws AlunoNaoEncontradoException, PessoaInvalidaException {
        Aluno aluno = new Aluno("Anna", "83777888999", "annab@email");
        gerenciamento.adicionaAluno(aluno);

        String matricula = aluno.getMatricula();

        assertTrue(gerenciamento.existeAluno(matricula));

        String dados = gerenciamento.consultaDadosAluno(matricula);

        assertTrue(dados.contains("Anna"));
        assertTrue(dados.contains("83777888999"));
        assertTrue(dados.contains("annab@email"));
    }

    @Test
    void existeAluno() throws PessoaInvalidaException {
        Aluno aluno = new Aluno("Anna", "83777888999", "annab@email");
        gerenciamento.adicionaAluno(aluno);

        String matricula = aluno.getMatricula();

        assertTrue(gerenciamento.existeAluno(matricula));
    }

    @Test
    void desativaAluno() throws AlunoNaoEncontradoException, PessoaInvalidaException {
        Aluno aluno = new Aluno("Anna", "83777888999", "annab@email");
        gerenciamento.adicionaAluno(aluno);

        String matricula = aluno.getMatricula();

        assertTrue(gerenciamento.existeAluno(matricula));

        gerenciamento.desativaAluno(matricula);

        assertFalse(gerenciamento.existeAluno(matricula));

    }
}