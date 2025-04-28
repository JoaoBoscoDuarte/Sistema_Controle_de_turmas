package testes;

import model.exceptions.PessoaInvalidaException;
import model.pessoa.Aluno;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PessoaTest {

    @Test
    void nomeInvalido() {
        assertThrows(PessoaInvalidaException.class, () -> {
            new Aluno("", "00111222333", "anna@email", "Si");
        });
    }

    @Test
    void telefoneInvalido() {
        assertThrows(PessoaInvalidaException.class, () -> {
            new Aluno("Anna", "0123", "anna@email", "SI");
        });
    }

    @Test
    void emailInvalido() {
        assertThrows(PessoaInvalidaException.class, () -> {
            new Aluno("Anna", "00111222333", "anna.email", "SI");
        });
    }

    @Test
    void testGets() throws PessoaInvalidaException {
        Aluno aluno = new Aluno("Anna", "00111222333", "anna@email", "SI");
        assertEquals("Anna", aluno.getNome());
        assertEquals("00111222333", aluno.getTelefone());
        assertEquals("anna@email", aluno.getEmail());
    }

    @Test
    void testSets() throws PessoaInvalidaException {
        Aluno aluno = new Aluno("Anna", "00111222333", "anna@email", "SI");

        aluno.setNome("Beatriz");
        assertEquals("Beatriz", aluno.getNome());

        aluno.setTelefone("44555666777");
        assertEquals("44555666777", aluno.getTelefone());

        aluno.setEmail("Bea@email");
        assertEquals("Bea@email", aluno.getEmail());
    }

    @Test
    void matriculaNula() throws PessoaInvalidaException {
        Aluno aluno = new Aluno("Anna", "00111222333", "anna@email", "SI");
        assertNotNull(aluno.getMatricula());
    }
}