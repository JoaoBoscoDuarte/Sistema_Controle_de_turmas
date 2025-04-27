package testes;

import model.exceptions.PessoaInvalidaException;
import model.pessoa.Aluno;
import model.servicos.GerenciamentoDeAlunos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GerenciamentoDeAlunosTest {
    GerenciamentoDeAlunos gerenciamento;

    @BeforeEach
    public void setUp() {
        gerenciamento = new GerenciamentoDeAlunos();
    }

    @Test
    void adicionaAluno() throws Exception {
        gerenciamento.adicionaAluno("Anna", "00111222333", "anna@email", "SI");
        assertEquals(1, gerenciamento.getListaAlunos().size());
    }

    @Test
    void getListaAlunos() throws PessoaInvalidaException {
        gerenciamento.adicionaAluno("Anna", "00111222333", "anna@email", "SI");
        gerenciamento.adicionaAluno("Joana", "00444555666", "darc@email", "SI");
        assertEquals(2,gerenciamento.getListaAlunos().size());

    }

    @Test
    void listaAlunos() throws Exception {
        gerenciamento.adicionaAluno("Anna", "00111222333", "anna@email", "SI");
        gerenciamento.adicionaAluno("Beatriz", "44555666777", "bea@email", "Sistema");
        String lista = gerenciamento.listarAlunos();
        assertTrue(lista.contains("Beatriz"));
        assertTrue(lista.contains("SI"));
    }

    @Test
    void consultaDadosAluno() throws Exception {
        gerenciamento.adicionaAluno("Anna", "00111222333", "anna@email", "SI");
        Aluno aluno = gerenciamento.getListaAlunos().get(0);
        String dados = gerenciamento.consultaDadosAluno(aluno.getMatricula());
        assertTrue(dados.contains("Anna"));
    }

    @Test
    void existeAluno() throws Exception {
        gerenciamento.adicionaAluno("Anna", "00111222333", "anna@email", "SI");
        Aluno aluno = gerenciamento.getListaAlunos().get(0);
        assertTrue(gerenciamento.existeAluno(aluno.getMatricula()));
    }

    @Test
    void desativaAluno() throws Exception {
        gerenciamento.adicionaAluno("Anna", "00111222333", "anna@email", "SI");
        Aluno aluno = gerenciamento.getListaAlunos().get(0);
        gerenciamento.desativaAluno(aluno.getMatricula());
        assertFalse(aluno.isAtivo());
    }

    @Test
    void buscaAluno () throws Exception {
        gerenciamento.adicionaAluno("Anna", "00111222333", "anna@email", "SI");
        Aluno aluno = gerenciamento.getListaAlunos().get(0);
        Aluno alunoEncontrado = gerenciamento.buscaAluno(aluno.getMatricula());
        assertNotNull(alunoEncontrado);
        assertEquals(aluno.getMatricula(), alunoEncontrado.getMatricula());
    }
}