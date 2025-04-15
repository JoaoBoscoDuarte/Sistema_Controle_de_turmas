package model.turma;

import model.pessoa.Aluno;

import java.util.HashMap;
import java.util.Map;

public class Nota {
    private Map<Integer, Double> notasPorUnidade; // Key: número da unidade, Value: nota
    private Aluno aluno;

    public Nota(Aluno aluno) {
        this.notasPorUnidade = new HashMap<>();
        this.aluno = aluno;
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

    public String getMatriculaAluno() {
        return aluno.getMatricula();
    }

    public Map<Integer, Double> getNotasPorUnidade() {
        return new HashMap<>(notasPorUnidade);
    }
}
