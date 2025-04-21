package model.servicos;

import model.disciplina.Disciplina;
import model.exceptions.*;

import java.util.*;

public class GerenciamentoDeDisciplinas {

    private final List<Disciplina> disciplinas;
    private final GerenciamentoDeProfessores gerenciadorProfessores;

    public GerenciamentoDeDisciplinas(GerenciamentoDeProfessores gerenciamentoDeProfessores) {
        this.disciplinas = new ArrayList<>();
        this.gerenciadorProfessores = gerenciamentoDeProfessores;
    }

    // Cadastra uma nova disciplina
    public void cadastraDisciplina(String nomeDisciplina, int cargaHoraria) throws DisciplinaJaCadastradaException, CargaHorariaInvalidaException, DisciplinaInvalidaException {
        if (nomeDisciplina == null) {
            throw new DisciplinaInvalidaException("Nome para disciplina inválido.");
        }

        if (cargaHoraria <= 0) {
            throw new CargaHorariaInvalidaException("Carga horária deve ser maior que zero.");
        }

        if (existeDisciplinaComMesmoNome(nomeDisciplina)) {
            throw new DisciplinaJaCadastradaException("Já existe uma disciplina cadastrada com o nome '" + nomeDisciplina + "'.");
        }

        this.disciplinas.add(new Disciplina(nomeDisciplina, cargaHoraria));
    }

    // Verifica se existe disciplina com o mesmo nome
    private boolean existeDisciplinaComMesmoNome(String nome) {
        for (Disciplina d : disciplinas) {
            if (d.getNomeDisciplina().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    // Lista todas as disciplinas
    public StringBuilder listaDisciplinas() {

        StringBuilder exibir = new StringBuilder();
        for (Disciplina d : disciplinas) {
            exibir.append(" | ").append(d);
        }

        return exibir;
    }

    // Associa professor a disciplina
    public void associarProfessorADisciplina(String nomeDisciplina, String matricula) throws DisciplinaInvalidaException, ProfessorNaoEncontradoException, DisciplinaNaoEncontradaException {

        if (nomeDisciplina == null || nomeDisciplina.trim().isEmpty()) {
            throw new DisciplinaInvalidaException("Nome para disciplina inválido.");
        }

        if (!gerenciadorProfessores.existeProfessor(matricula)) {
            throw new ProfessorNaoEncontradoException("Professor não existe");
        }

        // Verifica se a disciplina existe e associa ela ao professor
        Disciplina disciplina = procuraDisciplina(nomeDisciplina);
        disciplina.getProfessoresAssociados().add(matricula);
    }

    public Disciplina procuraDisciplina(String nomeDisciplina) throws DisciplinaInvalidaException, DisciplinaNaoEncontradaException {

        if (nomeDisciplina == null || nomeDisciplina.trim().isEmpty()) {
            throw new DisciplinaInvalidaException("Nome de disciplina inválido");
        }

        for (Disciplina d : disciplinas) {
            if (d.getNomeDisciplina().equalsIgnoreCase(nomeDisciplina)) {
                return d;
            }
        }

        throw new DisciplinaNaoEncontradaException("Disciplina com nome '" + nomeDisciplina + "' não encontrada.");
    }

    public boolean existeDisciplina(String nomeDisciplina) {
        for (Disciplina d : disciplinas) {
            if (d.getNomeDisciplina().equals(nomeDisciplina)) {
                return true;
            }
        }
        return false;
    }
}