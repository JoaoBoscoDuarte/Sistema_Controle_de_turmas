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

    public void cadastraDisciplina(Disciplina disciplina, String codigo, int cargaHoraria) throws Exception{
        if (existeDisciplinaComMesmoNome(disciplina.getNomeDisciplina())) {
            throw new Exception("Já existe uma disciplina cadastrada com o nome '" + disciplina.getNomeDisciplina() + "'.");
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

    public void associarProfessor(Disciplina disciplina, Professor professor) throws Exception{
        if (disciplina == null || professor == null) {
            throw new Exception("Disciplina e professor não podem ser nulos.");
        }
        if (!existeDisciplina(disciplina)) {
            throw new Exception("Disciplina não encontrada.");
        }
        if (!existeProfessor(professor)) {
            throw new Exception("Professor não encontrado.");
        }
    }

    public Disciplina procuraDisciplina(Disciplina nomeDisciplina) throws Exception{
        if (nomeDisciplina == null || nomeDisciplina.getNomeDisciplina() == null || nomeDisciplina.getNomeDisciplina().trim().isEmpty()) {
            throw new Exception("Nome da disciplina para a procura invalido.");
        }
        for (Disciplina disciplina : this.disciplinas) {
            if (disciplina.getNomeDisciplina().equalsIgnoreCase(nomeDisciplina.getNomeDisciplina())) {
                return disciplina;
            }
        }
        throw new Exception("Disciplina com nome '" + nomeDisciplina.getNomeDisciplina() + "' não encontrada.");
    }

    public boolean existeDisciplina(Disciplina disciplina) {
        return this.disciplinas.contains(disciplina);
    }

    public boolean existeProfessor(Professor professor) {
        return this.professores.contains(professor);
    }
}
