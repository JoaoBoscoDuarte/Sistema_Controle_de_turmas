package model.servicos;

import model.disciplina.Disciplina;
import model.exceptions.*;

import java.io.Serializable;
import java.util.*;

/*  ================| Só altere em caso de urgência! |====================
 *  ----------------------Classe 100% concluída-----------------------> OK
 */

public class GerenciamentoDeDisciplinas implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<Disciplina> disciplinas;
    private GerenciamentoDeProfessores gerenciadorProfessores;

    public GerenciamentoDeDisciplinas(GerenciamentoDeProfessores gerenciamentoDeProfessores) {
        this.disciplinas = new ArrayList<>();
        this.gerenciadorProfessores = gerenciamentoDeProfessores;
    }

    // Método que cadastra uma nova disciplina ---------------------------------------------> OK
    public void cadastraDisciplina(String nome,String codigo,int cargaHoraria) throws DisciplinaJaCadastradaException, CargaHorariaInvalidaException, NomeDaDisciplinaInvalidoException, DisciplinaInvalidaException {
        validaNomeDisciplina(nome);

        if(existeDisciplinaComMesmoCodigo(codigo)){
            throw new DisciplinaJaCadastradaException("Já existe uma disciplina com esse codigo.");
        }

        if (cargaHoraria <= 0) {
            throw new CargaHorariaInvalidaException("Carga horária deve ser maior que zero.");
        }

        if (nome == null) {
            throw new NomeDaDisciplinaInvalidoException("O nome da disciplina não pode ser vazil");
        }

        if (existeDisciplinaComMesmoNome(nome)) {
            throw new DisciplinaJaCadastradaException("Já existe uma disciplina cadastrada com o nome '" + nome + "'.");
        }

        this.disciplinas.add(new Disciplina(nome,codigo,cargaHoraria));
    }

    // Método que verifica se existe disciplina com o mesmo nome ---------------------------> OK
    private boolean existeDisciplinaComMesmoNome(String nome) {
        for (Disciplina d : disciplinas) {
            if (d.getNomeDisciplina().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    // Método que verifica se existe disciplina com o mesmo codigo ---------------------------> OK
    private boolean existeDisciplinaComMesmoCodigo(String codigo) {
        for (Disciplina d : disciplinas) {
            if (d.getNomeDisciplina().equals(codigo)) {
                return true;
            }
        }
        return false;
    }



    // Método que lista todas as disciplinas  ----------------------------------------------> OK
    public String listaDisciplinas() throws DisciplinaNaoEncontradaException{
        if (listaDisciplinas().isEmpty()) {
            throw new DisciplinaNaoEncontradaException("Lista de disciplinas vazia");
        }

        String exibir = "";
        for (Disciplina d : disciplinas) {
            exibir += d.toString();
        }

        return exibir;
    }

    // Método que associa professor a disciplina -------------------------------------------> OK
    public void associarProfessorADisciplina(String nome, String matricula) throws ProfessorNaoEncontradoException, DisciplinaNaoEncontradaException, DisciplinaInvalidaException {
        validaNomeDisciplina(nome);

        if (!gerenciadorProfessores.existeProfessor(matricula)) {
            throw new ProfessorNaoEncontradoException("Professor não existe");
        }

        // Verifica se a disciplina existe e associa ela ao professor
        Disciplina disciplina = buscaDisciplina(nome);
        disciplina.getProfessoresAssociados().add(matricula);
    }

    // Método que busca e retorna a disciplina caso exista ---------------------------------> OK
    public Disciplina buscaDisciplina(String nome) throws DisciplinaInvalidaException, DisciplinaNaoEncontradaException {
        validaNomeDisciplina(nome);
        for (Disciplina d : disciplinas) {
            if (d.getNomeDisciplina().equalsIgnoreCase(nome)) {
                return d;
            }
        }
        throw new DisciplinaNaoEncontradaException("Disciplina com nome '" + nome + "' não encontrada.");
    }

    // Método que verifica se a disciplina existe ------------------------------------------> OK
    public boolean existeDisciplina(String nomeDisciplina) {
        for (Disciplina d : disciplinas) {
            if (d.getNomeDisciplina().equals(nomeDisciplina)) {
                return true;
            }
        }
        return false;
    }

    // Método que valida e verifica com exceções nome da disciplina ------------------------> OK
    private void validaNomeDisciplina(String nome) throws DisciplinaInvalidaException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new DisciplinaInvalidaException("Nome da disciplina inválido.");
        }
    }

    // Métodos getters e setters necessários -----------------------------------------------> OK
    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }
}