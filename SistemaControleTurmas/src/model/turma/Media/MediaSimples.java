package model.turma.Media;

import java.util.List;

public class MediaSimples implements TiposDeMedia {
    private List<Double> listaDeNotas;

    public MediaSimples(List<Double> listaDeNotas) {
        this.listaDeNotas = listaDeNotas;
    }

    @Override
    public double calcularMedia() {
        double soma = 0;

        for (int i = 0; i < listaDeNotas.size(); i++) {
            soma += listaDeNotas.get(i);
        }

        return soma / listaDeNotas.size();
    }
}
