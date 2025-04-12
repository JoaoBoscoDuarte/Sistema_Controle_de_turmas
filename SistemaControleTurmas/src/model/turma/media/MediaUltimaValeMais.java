package model.turma.media;

import model.turma.Nota;
import java.util.List;

public class MediaUltimaValeMais implements TiposDeMedia {
    public double calcularMedia(List<Double> notas) {
        double ultimaNota = notas.size() - 1;
        double soma = 0;

        for (int i = 0; i < notas.size(); i++) {
            soma += notas.get(i);
        }

        return (0.5 * ultimaNota) + (0.5 * (soma / (notas.size() - 1)));
    }
}
