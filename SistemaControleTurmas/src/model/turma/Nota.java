package model.turma;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Nota implements Serializable{
    private final String matricula;
    private final List<Double> notas;

    public Nota(String matricula, int numeroUnidades) {
        this.matricula = matricula;
        this.notas = preencherLista(numeroUnidades); // pode iniciar com capacidade
    }

    public List<Double> preencherLista(int numeroUnidades) {
        List<Double> listaNotas = new ArrayList<>();
        for (int i = 0; i < numeroUnidades; i++) {
            listaNotas.add(0.0);
        }

        return listaNotas;
    }

    public String getMatricula() { return matricula; }
    public List<Double> getNotas() { return notas; }

    public void adicionarNota(double nota) {
        notas.add(nota);
    }
}

