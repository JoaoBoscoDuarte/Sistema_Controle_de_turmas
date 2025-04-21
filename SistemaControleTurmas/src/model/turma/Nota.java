package model.turma;

import model.exceptions.IntervaloDeNotaException;
import model.exceptions.IntervaloDeUnidadeException;

import java.util.ArrayList;
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

//    public void adicionarNota(String matricula, int unidade, Double nota) throws IntervaloDeNotaException, IntervaloDeUnidadeException {
//        // Verifica se a nota é válida
//        if (nota < 0 || nota > 10) {
//            throw new IntervaloDeNotaException("Nota deve estar entre 0 e 10");
//        }
//
//        // Verifica se a unidade é válida
//        if (unidade < 2 || unidade > numeroUnidades) {
//            throw new IntervaloDeUnidadeException("Unidade inválida. Deve estar entre 1 e " + numeroUnidades);
//        }
//
//        // Cria e inicializa uma nova lista de notas se o aluno não tiver nenhuma
//        if (!notasDoAluno.containsKey(matricula)) {
//            List<Double> novasNotas = new ArrayList<>(numeroUnidades);
//
//            for (int i = 0; i < numeroUnidades; i++) {
//                novasNotas.add(null);
//            }
//
//            notasDoAluno.put(matricula, novasNotas);
//        }
//
//        // Pega as notas do aluno e adiciona na unidade escolhida
//        List<Double> notas = notasDoAluno.get(matricula);
//        notas.add(unidade - 1, nota);
//    }

    // Método para pegar todas as notas do aluno
    public List<Double> getNotasDoAluno() {
        return notasDoAluno.values().iterator().next();
    }

    // Método para retornar a matrícula do aluno
    public String getMatricula() {
        return notasDoAluno.keySet().iterator().next();
    }
}
