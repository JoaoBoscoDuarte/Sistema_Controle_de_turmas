package model.turma.Media;

import java.util.List;

public class MediaUltimaValeMais implements TiposDeMedia {
    private List<Double> listaDeNotas;

    public MediaUltimaValeMais(List<Double> listaDeNotas) {
        this.listaDeNotas = listaDeNotas;
    }

    @Override
    public double calcularMedia() {
        double ultimaNota = listaDeNotas.get(listaDeNotas.size() - 1);
        double soma = 0;

        for (int i = 0; i < listaDeNotas.size(); i++) {
            soma += listaDeNotas.get(i);
        }

        return (0.5 * ultimaNota) + (0.5 * (soma / (listaDeNotas.size() - 1)));
    }
}
