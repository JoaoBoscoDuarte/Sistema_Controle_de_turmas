package testes;

import model.exceptions.PessoaInvalidaException;
import model.pessoa.Aluno;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AlunoTest {

    @Test
    void validaCurso() {
        assertThrows(PessoaInvalidaException.class, () -> {
            new Aluno("Anna", "00111222333", "anna@email", "");
        });
    }

    @Test
    void getCurso() throws PessoaInvalidaException {
        Aluno aluno = new Aluno("Anna", "00111222333", "anna@email", "SI");
        assertEquals("SI", aluno.getCurso());
    }

    @Test
    void setCurso() throws PessoaInvalidaException {
        Aluno aluno = new Aluno("Anna", "00111222333", "anna@email", "SI");
        aluno.setCurso("adm");
        assertEquals("adm", aluno.getCurso());
    }
}