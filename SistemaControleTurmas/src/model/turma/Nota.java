package model.turma;

import model.pessoa.Aluno;
import java.util.List;

public class Nota {
    private Aluno aluno;
    private List<Double> notasDoAluno;

    public Nota(Aluno aluno, List<Double> notasDoAluno) {
        this.aluno = aluno;
        this.notasDoAluno = notasDoAluno;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public List<Double> getNotasDoAluno() {
        return notasDoAluno;
    }
}
