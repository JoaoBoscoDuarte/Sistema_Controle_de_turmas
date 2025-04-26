package model.turma;

import java.util.ArrayList;
import java.util.List;

public class Nota {
    private static final long serialVersionUID = 1L;

    private final String matricula;
    private final List<Double> notas;

    public Nota(String matricula, int numeroUnidades) {
        this.matricula = matricula;
        this.notas = new ArrayList<>(numeroUnidades); // pode iniciar com capacidade
    }

    public String getMatricula() { return matricula; }
    public List<Double> getNotas() { return notas; }

    public void adicionarNota(double nota) {
        notas.add(nota);
    }
}

