package model.turma;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nota {
    private final Map<String, List<Double>> notasDoAluno; // Key: Matrículo do aluno, Value: notas
    private final int numeroUnidades;                     // Tamanho da Array = nº Unidades

    public Nota(int numeroUnidades) {
        this.notasDoAluno = new HashMap<>();
        this.numeroUnidades = numeroUnidades;
    }

    public Map<String, List<Double>> getNotasDoAluno() {
        return notasDoAluno;
    }

    public int getNumeroUnidades() {
        return numeroUnidades;
    }
}
