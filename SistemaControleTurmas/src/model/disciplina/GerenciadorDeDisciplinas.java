package model.disciplina;

import model.pessoa.Professor;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeDisciplinas {
    private List<Disciplina> disciplinas;
    private List<Professor> professores;

    public GerenciadorDeDisciplinas() {
        this.disciplinas = new ArrayList<>();
        this.professores = new ArrayList<>();
    }

    public void cadastraDisciplina(Disciplina disciplina, String codigo, int cargaHoraria) {
        if (existeDisciplinaComMesmoNome(disciplina.getNomeDisciplina())) {
            System.out.println("Erro já existe uma disciplina cadastrada com esse nome " + disciplina.getNomeDisciplina() + ".");
            return;
        }
        disciplina.setCodigo(codigo);
        disciplina.setCargaHoraria(cargaHoraria);
        this.disciplinas.add(disciplina);
    }

    private boolean existeDisciplinaComMesmoNome(String nome) {
        for (Disciplina d : this.disciplinas) {
            if (d.getNomeDisciplina().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    public String listaDisciplinas(List<Disciplina> listaDeDisciplinas) {
        if (listaDeDisciplinas.isEmpty()) {
            return "Não tem disciplinas cadastradas.";
        }
        List<Disciplina> resultado = new ArrayList<>();
        for (Disciplina disciplina : disciplinas) {
            resultado.add(disciplina);
        }
        return resultado.toString();
    }

    public void associarProfessor(Disciplina disciplina, Professor professor) {
        if (disciplina != null && professor != null && existeDisciplina(disciplina) && existeProfessor(professor)) {
            System.out.println("Professor " + professor.getNome() + " associado a disciplina " + disciplina.getNomeDisciplina() + ".");
        } else {
            System.out.println("Não deu pra associar: disciplina ou professor inválido ou não cadastradoo.");
        }
    }

    public Disciplina procuraDisciplina(Disciplina nomeDisciplina) {
        for (Disciplina disciplina : this.disciplinas) {
            if (disciplina.equals(nomeDisciplina)) {
                return disciplina;
            }
        }
        return null;
    }

    public boolean existeDisciplina(Disciplina disciplina) {
        return this.disciplinas.contains(disciplina);
    }

    public boolean existeProfessor(Professor professor) {
        return this.professores.contains(professor);
    }
}

