package model.turma.media;

import java.io.Serializable;
import java.util.List;

/*  ================| Só altere em caso de urgência! |====================
 *  ----------------------Classe 100% concluída-----------------------> OK
 */

public class MediaUltimaValeMais implements TiposDeMediaIF, Serializable {

    @Override
    public double calcularMedia(List<Double> notas) {

        double ultimaNota = notas.get(notas.size() - 1);
        double soma = 0;

        for (int i = 0; i < notas.size() - 1 ; i++) {
            soma += notas.get(i);
        }

        return (0.5 * ultimaNota) + (0.5 * (soma / (notas.size() - 1)));
    }
}
