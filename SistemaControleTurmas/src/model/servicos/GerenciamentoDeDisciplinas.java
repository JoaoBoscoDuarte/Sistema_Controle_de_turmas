package model.servicos;

import model.disciplina.Disciplina;
import model.faculdade.Faculdade;
import model.pessoa.Professor;

import java.util.List;
import java.util.Objects;

public class GerenciamentoDeDisciplinas {
    private Faculdade faculdade;
    private List<Disciplina> disciplinas;
    private List<Professor> professores;

    public GerenciamentoDeDisciplinas() {
        this.professores = faculdade.getColecaoProfessor();
    }

    public static class DisciplinaJaCadastradaException extends Exception {
        public DisciplinaJaCadastradaException(String message) {
            super(message);
        }
    }
    public static class DisciplinaNaoEncontradaException extends Exception {
        public DisciplinaNaoEncontradaException(String message) {
            super(message);
        }
    }
    public static class ProfessorNaoEncontradoException extends Exception {
        public ProfessorNaoEncontradoException(String message) {
            super(message);
        }
    }
    public static class AssociacaoInvalidaException extends Exception {
        public AssociacaoInvalidaException(String message) {
            super(message);
        }
    }

    public void cadastraDisciplina(Disciplina disciplina, int cargaHoraria) throws DisciplinaJaCadastradaException {
        Objects.requireNonNull(disciplina, "Disciplina não pode ser nula.");
        validaNomeDisciplina(disciplina.getNomeDisciplina());
        disciplina.setCargaHoraria(cargaHoraria);
        this.disciplinas.add(disciplina);
    }

    private void validaNomeDisciplina(String nome) throws DisciplinaJaCadastradaException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da disciplina não pode ser nulo ou vazio.");
        }
        if (existeDisciplinaComMesmoNome(nome)) {
            throw new DisciplinaJaCadastradaException("Já existe uma disciplina cadastrada com o nome '" + nome + "'.");
        }
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
        if (listaDeDisciplinas == null || listaDeDisciplinas.isEmpty()) {
            return "Não tem disciplinas cadastradas.";
        }
        return listaDeDisciplinas.toString();
    }

    public void associarProfessor(Disciplina disciplina, Professor professor) throws DisciplinaNaoEncontradaException, ProfessorNaoEncontradoException, AssociacaoInvalidaException {
        Objects.requireNonNull(disciplina, "Disciplina não pode ser nula.");
        Objects.requireNonNull(professor, "Professor não pode ser nulo.");

        if (!existeDisciplina(disciplina)) {
            throw new DisciplinaNaoEncontradaException("Disciplina não encontrada.");
        }
        if (!existeProfessor(professor)) {
            throw new ProfessorNaoEncontradoException("Professor não encontrado.");
        }
        // Implementar a associação de professor!!!!
    }

    public Disciplina procuraDisciplina(String nomeDisciplina) throws DisciplinaNaoEncontradaException {
        if (nomeDisciplina == null || nomeDisciplina.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da disciplina para a procura não pode ser nulo ou vazio.");
        }
        for (Disciplina disciplina : this.disciplinas) {
            if (disciplina.getNomeDisciplina().equalsIgnoreCase(nomeDisciplina)) {
                return disciplina;
            }
        }
        throw new DisciplinaNaoEncontradaException("Disciplina com nome '" + nomeDisciplina + "' não encontrada.");
    }

    public boolean existeDisciplina(Disciplina disciplina) {
        return this.disciplinas.contains(disciplina);
    }

    public boolean existeProfessor(Professor professor) {
        return this.professores.contains(professor);
    }

    public Faculdade getFaculdade() {
        return faculdade;
    }

    public void setFaculdade(Faculdade faculdade) {
        this.faculdade = faculdade;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }
}
