package testes;

import model.pessoa.Pessoa;
import model.exceptions.PessoaInvalidaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PessoaTest {

    //classe abstrata, adicionar pessoa valida, invalidez NOME TELEFONE EMAIL, desativar uma pessoa

    private Pessoa pessoa;

    @BeforeEach
    void inicializar() throws PessoaInvalidaException {
        pessoa = new Pessoa("Bea", "00111222333", "beatriz@email");
    }

    @Test
    void adicionaPessoaValida() {
        assertEquals("Bea", pessoa.getNome());
        assertEquals("00111222333", pessoa.getTelefone());
        assertEquals("beatriz@email", pessoa.getEmail());

        assertTrue(pessoa.isAtivo());
        //vai ficar assim mesmo essa bomba
    }

    @Test
    void nomeInvalido() {
        try {
            new Pessoa("", "00111222333", "beatriz@email");
            fail("Esperava PessoaInvalidaException para nome inválido");
        } catch (PessoaInvalidaException e) {
            assertEquals("Nome inválido. O nome não pode estar vazio", e.getMessage());
        }
    }

    @Test
    void telefoneInvalido() {
        try {
            new Pessoa("Bea", "001112233", "beatriz@email");
            fail();
        } catch (PessoaInvalidaException e) {
            assertEquals("Número de telefone inválido. Não pode estar vazio e deve conter 11 digitos", e.getMessage());
        }
    }

    @Test
    void emailInvalido() {
        try {
            new Pessoa("Bea", "00111222333", "beatriz.email");
        } catch (PessoaInvalidaException e) {
            assertEquals("E-mail inválido. Não pode estar vazio ou sem o uso de '@'", e.getMessage());
        }
    }

    @Test
    void desativaPessoa() throws PessoaInvalidaException {
        Pessoa pessoa = new Pessoa("Bea", "00111222333", "beatriz@email");
        pessoa.invalidar();
        assertFalse(pessoa.isAtivo());
    }
}