package model.turma.media;

import java.util.List;

public class MediaDescartaMenorNota implements TiposDeMediaIF {

    public double calcularMedia(List<Double> notas) {
        double menorNota = notas.get(0);
        double soma = 0;

        for (int i = 0; i < notas.size(); i++) {
            if (menorNota > notas.get(i)) {
                menorNota = notas.get(i);
            }
            soma += notas.get(i);
        }

        return (soma - menorNota)/notas.size() - 1;
    }
}
