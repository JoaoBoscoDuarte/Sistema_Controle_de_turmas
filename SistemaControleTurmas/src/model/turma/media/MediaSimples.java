package model.turma.media;

import java.util.List;

/*  ================| Só altere em caso de urgência! |====================
 *  ----------------------Classe 100% concluída-----------------------> OK
 */

public class MediaSimples implements TiposDeMediaIF {

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
