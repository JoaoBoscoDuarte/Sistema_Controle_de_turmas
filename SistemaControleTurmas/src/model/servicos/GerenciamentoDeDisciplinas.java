package model.servicos;

import model.disciplina.Disciplina;
import model.faculdade.Faculdade;
import model.pessoa.Professor;
import model.exceptions.DisciplinaJaCadastradaException;
import model.exceptions.DisciplinaNaoEncontradaException;
import model.exceptions.ProfessorNaoEncontradoException;
import model.exceptions.AssociacaoInvalidaException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GerenciamentoDeDisciplinas {
    private Faculdade faculdade;
    private List<Disciplina> disciplinas;
    private final GerenciamentoDeProfessores gerenciamentoDeProfessores;
    private final GerenciamentoDeTurmas gerenciamentoDeTurmas;

    public GerenciamentoDeDisciplinas(GerenciamentoDeProfessores gerenciamentoDeProfessores, GerenciamentoDeTurmas gerenciamentoDeTurmas) {
        this.gerenciamentoDeProfessores = Objects.requireNonNull(gerenciamentoDeProfessores, "");
        this.gerenciamentoDeTurmas = Objects.requireNonNull(gerenciamentoDeTurmas, "Gerenciamento de Turmas não pode ser nulo.");
        this.disciplinas = new ArrayList<>();
    }

    public void cadastraDisciplina(String nomeDisciplina, int cargaHoraria) throws DisciplinaJaCadastradaException {
        Objects.requireNonNull(nomeDisciplina, "Nome da disciplina não pode ser nulo.");

        if (nomeDisciplina.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da disciplina não pode ser vazio.");
        }
        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("Carga horária deve ser maior que zero.");
        }

        if (existeDisciplinaComMesmoNome(nomeDisciplina)) {
            throw new DisciplinaJaCadastradaException("Já existe uma disciplina cadastrada com o nome '" + nomeDisciplina + "'.");
        }

        Disciplina novaDisciplina = new Disciplina(nomeDisciplina, null, cargaHoraria);
        this.disciplinas.add(novaDisciplina);
    }

    private boolean existeDisciplinaComMesmoNome(String nome) {
        for (Disciplina d : this.disciplinas) {
            if (d.getNomeDisciplina().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    public String listaDisciplinas() {
        if (disciplinas == null || disciplinas.isEmpty()) {
            return "Não tem disciplinas cadastradas.";
        }
        return disciplinas.toString();
    }

    public void associarProfessor(Disciplina disciplina, Professor professor) throws DisciplinaNaoEncontradaException, ProfessorNaoEncontradoException, AssociacaoInvalidaException {
        Objects.requireNonNull(disciplina, "Disciplina não pode ser nula.");
        Objects.requireNonNull(professor, "Professor não pode ser nula.");

        Disciplina disciplinaEncontrada = procuraDisciplina(disciplina.getNomeDisciplina());
        if (disciplinaEncontrada == null) {
            throw new DisciplinaNaoEncontradaException("Disciplina não encontrada.");
        }

        Professor professorEncontrado = gerenciamentoDeProfessores.buscarProfessorPorMatricula(professor.getMatricula());
        if (professorEncontrado == null) {
            throw new ProfessorNaoEncontradoException("Professor com matrícula '" + professor.getMatricula() + "' não encontrado.");
        }

        disciplinaEncontrada.adicionarProfessorAssociado(professorEncontrado);
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

}