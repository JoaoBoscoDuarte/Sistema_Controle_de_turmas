package model.pessoa;

import model.exceptions.PessoaInvalidaException;

import java.util.Objects;

public class Aluno extends Pessoa {
    private String curso;

    public Aluno(String nome, String telefone, String email, String curso) throws PessoaInvalidaException {
        super(nome, telefone, email);
        validaCurso(curso);
        this.curso = curso;
    }

    private void validaCurso(String curso) throws PessoaInvalidaException {
        if (curso == null || curso.isEmpty()) {
            throw new PessoaInvalidaException("Inválido. O nome do curso não pode estar vazio.");
        }
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) throws PessoaInvalidaException {
        validaCurso(curso);
        this.curso = curso;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(curso, aluno.curso);

    }
    @Override
    public String toString() {
        return "ALUNO: " +
                "| Nome: " + getNome() + " | \n" +
                "| Telefone: " + getTelefone() + " | \n" +
                "| Email: " + getEmail() + " | \n" +
                "| Matricula: " + getMatricula() + " | \n" +
                "| Curso: " + curso + " | \n";
    }
}