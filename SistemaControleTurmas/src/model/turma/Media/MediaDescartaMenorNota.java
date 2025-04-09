package model.turma.Media;

import java.util.List;

public class MediaDescartaMenorNota implements TiposDeMedia {
    private List<Double> listaDeNotas;

    public MediaDescartaMenorNota(List<Double> listaDeNotas) {
        this.listaDeNotas = listaDeNotas;
    }

    @Override
    public double calcularMedia() {
        double menorNota = listaDeNotas.get(0);
        double soma = 0;

        for (int i = 0; i < listaDeNotas.size(); i++) {
            if (menorNota > listaDeNotas.get(i)) {
                menorNota = listaDeNotas.get(i);
            }

            soma += listaDeNotas.get(i);
        }

        return (soma - menorNota)/listaDeNotas.size() - 1;
    }
}
