package model.pessoa;

import java.time.LocalDate;
import java.util.Objects;

public class Aluno extends Pessoa {
    private String matricula;
    private LocalDate dataCriacao;
    private static int contaMatricula = 1;

    public Aluno(String nome, String telefone, String email) throws Exception {
        super(nome, telefone, email);
        this.matricula = geradorDeMatricula();
        this.dataCriacao = LocalDate.now();
    }

    private String geradorDeMatricula() {
        int anoMatricula = LocalDate.now().getYear();
        int numeroMatricula = contaMatricula++;
        return anoMatricula + "" + contaMatricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "matricula='" + matricula + '\'' +
                ", dataCriacao=" + dataCriacao +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(matricula, aluno.matricula) && Objects.equals(dataCriacao, aluno.dataCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), matricula, dataCriacao);
    }
}