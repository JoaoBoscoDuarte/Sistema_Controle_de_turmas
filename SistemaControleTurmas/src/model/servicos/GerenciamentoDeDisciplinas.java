package model.servicos;

import model.disciplina.Disciplina;
import model.exceptions.*;

import java.io.Serializable;
import java.util.*;

/*  ================| Só altere em caso de urgência! |====================
 *  ----------------------Classe 100% concluída-----------------------> OK
 */

public class GerenciamentoDeDisciplinas implements Serializable {
    private final List<Disciplina> disciplinas;
    private GerenciamentoDeProfessores gerenciadorProfessores;

    public GerenciamentoDeDisciplinas(GerenciamentoDeProfessores gerenciamentoDeProfessores) {
        this.disciplinas = new ArrayList<>();
        this.gerenciadorProfessores = gerenciamentoDeProfessores;
    }

    // Cadastra disciplina (Cria disciplina) -----------------------------------------------------------------------> OK
    public void cadastraDisciplina(String nome,String codigo,int cargaHoraria) throws DisciplinaJaCadastradaException, CargaHorariaInvalidaException, NomeDaDisciplinaInvalidoException, DisciplinaInvalidaException {
        validaNomeDisciplina(nome);

        if(existeDisciplina(codigo)){
            throw new DisciplinaJaCadastradaException("Já existe uma disciplina com esse codigo.");
        }

        if (cargaHoraria <= 0) {
            throw new CargaHorariaInvalidaException("Carga horária deve ser maior que zero.");
        }

        if (nome == null) {
            throw new NomeDaDisciplinaInvalidoException("O nome da disciplina não pode ser vazil");
        }

        if (existeDisciplina(nome)) {
            throw new DisciplinaJaCadastradaException("Já existe uma disciplina cadastrada com o nome '" + nome + "'.");
        }

        this.disciplinas.add(new Disciplina(nome, codigo, cargaHoraria));
    }

    // Lista todas as disciplinas da faculdade em tela -------------------------------------------------------------> OK
    public String listarDisciplinas() throws DisciplinaNaoEncontradaException{
        if (disciplinas.isEmpty()) {
            throw new DisciplinaNaoEncontradaException("Lista de disciplinas vazia");
        }

        String exibir = "";
        for (Disciplina d : disciplinas) {
            exibir += d.toString() + "\n";
        }

        return exibir;
    }

    // Associa o professor a disciplina (adiciona o professor a lista da disciplina e vice e versa) ----------------> OK
    public void associarProfessorADisciplina(List<Disciplina> disciplinas, String matricula) throws ProfessorNaoEncontradoException {
        if (!gerenciadorProfessores.existeProfessor(matricula)) {
            throw new ProfessorNaoEncontradoException("Professor não existe");
        }

        // Verifica se a disciplina existe e associa ela ao professor
        for (Disciplina d : disciplinas) {
            d.getProfessoresAssociados().add(gerenciadorProfessores.buscaProfessor(matricula));
        }
    }

    // Busca a disciplina pelo nome (Retorna o objeto disciplina pelo nome dela) -----------------------------------> OK
    public Disciplina buscaDisciplina(String nome) throws DisciplinaInvalidaException, DisciplinaNaoEncontradaException {
        validaNomeDisciplina(nome);
        for (Disciplina d : disciplinas) {
            if (d.getNomeDisciplina().equalsIgnoreCase(nome)) {
                return d;
            }
        }
        throw new DisciplinaNaoEncontradaException("Disciplina com nome '" + nome + "' não encontrada.");
    }

    // Verifica se a disciplina existe -----------------------------------------------------------------------------> Ok
    public boolean existeDisciplina(String nomeDisciplina) {
        for (Disciplina d : disciplinas) {
            if (d.getNomeDisciplina().toLowerCase().replaceAll("\\s+", "").equals(nomeDisciplina.toLowerCase().replaceAll("\\s+", ""))) {
                return true;
            }
        }
        return false;
    }

    // Condensa algumas verificaçãoes do nome da disciplina --------------------------------------------------------> OK
    private void validaNomeDisciplina(String nome) throws DisciplinaInvalidaException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new DisciplinaInvalidaException("Nome da disciplina inválido.");
        }
    }

    // Métodos getters e setters necessários -----------------------------------------------------------------------> OK
    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }
}