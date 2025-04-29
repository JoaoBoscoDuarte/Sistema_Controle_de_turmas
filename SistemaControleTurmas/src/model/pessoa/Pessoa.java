package model.pessoa;

import model.exceptions.PessoaInvalidaException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/*  ================| Só altere em caso de urgência! |====================
 *  --------------------Classe 100% concluída-------------------------> OK
 */

public abstract class Pessoa implements Serializable {
    private String nome;
    private String telefone;
    private String email;
    private boolean ativo = true;
    private final String matricula;
    private static int contaMatricula = 1;

    public Pessoa(String nome, String telefone, String email) throws PessoaInvalidaException {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.matricula = geradorDeMatricula();
        validaNome(nome);
        validaTelefone(telefone);
        validaEmail(email);
    }

    private void validaNome(String nome) throws PessoaInvalidaException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new PessoaInvalidaException("Nome inválido. O nome não pode estar vazio.");
        }
    }

    private void validaTelefone(String telefone) throws PessoaInvalidaException {
        if (telefone == null || telefone.trim().isEmpty() || telefone.length() < 11) {
            throw new PessoaInvalidaException("Número de telefone inválido. Deve conter pelo menos 11 digitos.");
        }
    }

    private void validaEmail(String email) throws PessoaInvalidaException {
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new PessoaInvalidaException("E-mail inválido. O e-mail não pode estar vazio ou sem '@'.");
        }
    }

    private String geradorDeMatricula() {
        return LocalDate.now().getYear() + String.format("%04d", contaMatricula++);
    }

    public boolean isAtivo() {
        return ativo;
    }

    // Getters e setters --------------------------------------------> OK
    public static int getContaMatricula() {
        return contaMatricula;
    }

    public static void setContaMatricula(int contaMatricula) {
        Pessoa.contaMatricula = contaMatricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws PessoaInvalidaException {
        validaNome(nome);
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) throws PessoaInvalidaException {
        validaTelefone(telefone);
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws PessoaInvalidaException {
        validaEmail(email);
        this.email = email;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getMatricula() {
        return matricula;
    }

    // Métodos básicos ----------------------------------------------> OK
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

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", ativo=" + ativo +
                '}';
    }
}