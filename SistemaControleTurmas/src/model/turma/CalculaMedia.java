package model.turma;

import java.util.ArrayList;
import java.util.List;

public class CalculaMedia {

    private List<Double> notas;
    private int qtdUnidadesAvaliativas;

    public CalculaMedia(List<Double> notas, int qtdUnidadesAvaliativas) {
        this.notas = notas;
        this.qtdUnidadesAvaliativas = qtdUnidadesAvaliativas;
    }

    public double mediaSimples() {
        double calculo = 0;

        for (int i = 0; i < notas.size(); i++) {
            calculo += notas.get(i);
        }

        return calculo/qtdUnidadesAvaliativas;
    }

    public double mediaUltimaNotaValeMais() {
        double peso = 0.5;
        double calculo = 0;
        double ultimaNota = notas.get(notas.size() - 1);

        for (int i = 0; i < notas.size() - 1; i++) {
            calculo += notas.get(i);
        }

        return ((peso * ultimaNota) + (peso * (calculo / notas.size() - 1)));
    }

    public double mediaDescartaMenor() {
        double soma = 0;
        double menorNota = notas.get(0);

        for (double nota : notas) {
            soma += nota;

            if (nota < menorNota) {
                menorNota = nota;
            }
        }
        soma -= menorNota;

        return (soma / (notas.size() - 1));
    }

    public String verificaAprovacao(double nota) {
        if (nota >= 7) {
            return "APROVADO";

        } else if (nota < 4) {
            return "REPROVADO";

        } else {
            return "RECUPERAÇÃO";
        }
    }
}