package model.disciplina;

import java.time.LocalDate;
import java.util.Objects;

public class Disciplina {
    private String nomeDisciplina;
    private String codigo;
    private int cargaHoraria;
    private LocalDate dataDeCriacao;
    private static int contador = 1;


    public Disciplina(String nomeDisciplina, String codigo, int cargaHoraria) {
        this.nomeDisciplina = nomeDisciplina;
        this.codigo = gerarCodigoDisciplina();
        this.cargaHoraria = cargaHoraria;
        this.dataDeCriacao = LocalDate.now();
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String gerarCodigoDisciplina() {
        int anoMatricola = LocalDate.now().getYear();
        int codigo = contador++;
        return anoMatricola + contador + "";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Disciplina that = (Disciplina) o;
        return cargaHoraria == that.cargaHoraria && Objects.equals(nomeDisciplina, that.nomeDisciplina) && Objects.equals(codigo, that.codigo) && Objects.equals(dataDeCriacao, that.dataDeCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeDisciplina, codigo, cargaHoraria, dataDeCriacao);
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "nomeDisciplina='" + nomeDisciplina + '\'' +
                ", codigo='" + codigo + '\'' +
                ", cargaHoraria=" + cargaHoraria +
                ", dataDeCriacao=" + dataDeCriacao +
                '}';
    }
}
