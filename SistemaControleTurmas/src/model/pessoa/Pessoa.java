package model.pessoa;

import model.exceptions.PessoaInvalidaException;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Pessoa implements PessoaIF {
    private String nome;
    private String telefone;
    private String email;
    private boolean ativo = false;
    private final String matricula;
    private LocalDate dataCriacao;
    private static int contaMatricula = 1;

    public Pessoa(String nome, String telefone, String email) throws PessoaInvalidaException {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.matricula = geradorDeMatricula();
        this.dataCriacao = LocalDate.now();
        validaNome(nome);
        validaTelefone(telefone);
        validaEmail(email);
    }

    private void validaNome(String nome) throws PessoaInvalidaException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new PessoaInvalidaException("Nome inválido");
        }
    }

    private void validaTelefone(String telefone) throws PessoaInvalidaException {
        if (telefone == null || telefone.trim().isEmpty() || telefone.length() != 11) {
            throw new PessoaInvalidaException("Número de telefone inválido. Deve conter 11 digitos.");
        }
    }

    private void validaEmail(String email) throws PessoaInvalidaException {
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new PessoaInvalidaException("E-mail inválido.");
        }

    }

    private String geradorDeMatricula() {
        return LocalDate.now().getYear() + String.format("%04d" , contaMatricula++);
    }

    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws PessoaInvalidaException {
        validaNome(nome);
        this.nome = nome;
    }

    @Override
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) throws PessoaInvalidaException {
        validaTelefone(telefone);
        this.telefone = telefone;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws PessoaInvalidaException {
        validaEmail(email);
        this.email = email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getMatricula() {
        return matricula;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void validar() {
        this.ativo = true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return ativo == pessoa.ativo && Objects.equals(nome, pessoa.nome) && Objects.equals(telefone, pessoa.telefone) && Objects.equals(email, pessoa.email) && Objects.equals(matricula, pessoa.matricula) && Objects.equals(dataCriacao, pessoa.dataCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, telefone, email, ativo, matricula, dataCriacao);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", ativo=" + ativo +
                ", matricula='" + matricula + '\'' +
                ", dataCriacao=" + dataCriacao +
                '}';
    }
}