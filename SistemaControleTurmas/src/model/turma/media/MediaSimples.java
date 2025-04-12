package model.turma.media;

import model.turma.Nota;

import java.util.ArrayList;
import java.util.List;

public class MediaSimples implements TiposDeMedia {

    @Override
    public double calcularMedia(List<Double> notas) {
        if (notas == null || notas.isEmpty()) {
            return 0.0;
        }

        double soma = 0;
        for (Double nota : notas) {
            soma += nota;
        }
        return soma / notas.size();
    }
}
