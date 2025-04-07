package model.pessoa;

import java.util.Objects;

public class Pessoa implements PessoaIF {
    private String nome;
    private String telefone;
    private String email;
    private boolean ativo = false;

    public Pessoa(String nome, String telefone, String email) throws Exception {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        validaNome(nome);
        validaTelefone(telefone);
        validaEmail(email);
    }

    private void validaNome(String nome) throws Exception {
        if (nome == null || nome.trim().isEmpty()) {
            throw new Exception("Nome inválido");
        }
    }

    private void validaTelefone(String telefone) throws Exception {
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new Exception("Número de telefone inválido");
        }
    }

    private void validaEmail(String email) throws Exception {
        if (email == null || email.trim().isEmpty()) {
            throw new Exception("E-mail inválido");
        }
    }

    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getTelefone() {
        return "";
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String getEmail() {
        return "";
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void validar() {
        this.ativo = true;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", ativo=" + ativo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return ativo == pessoa.ativo && Objects.equals(nome, pessoa.nome) && Objects.equals(telefone, pessoa.telefone) && Objects.equals(email, pessoa.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, telefone, email, ativo);
    }
}