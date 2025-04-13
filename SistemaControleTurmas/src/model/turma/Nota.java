package model.turma;

import model.pessoa.Aluno;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nota {
    private Aluno aluno;
    private Map<Integer, Double> notasPorUnidade; // Key: número da unidade, Value: nota

    public Nota(Aluno aluno) {
        this.aluno = aluno;
        this.notasPorUnidade = new HashMap<>();
    }

    public void adicionarNota(int unidade, double nota) {
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("Nota deve estar entre 0 e 10");
        }
        notasPorUnidade.put(unidade, nota);
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Map<Integer, Double> getNotasPorUnidade() {
        return new HashMap<>(notasPorUnidade);
    }
}
