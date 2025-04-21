package model.pessoa;
import model.exceptions.PessoaInvalidaException;
import model.turma.Turma;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Professor extends Pessoa{

    public Professor(String nome, String telefone, String email) throws Exception {
        super(nome, telefone, email);
    }
}
