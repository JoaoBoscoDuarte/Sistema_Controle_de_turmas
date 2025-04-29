package model.servicos;

import model.disciplina.Disciplina;
import model.exceptions.ProfessorNaoEncontradoException;
import model.pessoa.Professor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*  ================| Só altere em caso de urgência! |====================
 *  ----------------------Classe 100% concluída-----------------------> OK
 */

public class GerenciamentoDeProfessores implements Serializable {

    private final List<Professor> LISTA_DE_PROFESSORES = new ArrayList<>();

    // Adiciona professor sem disciplinas (usuário não mandou as disciplinas aqui) ---------------------------------> Ok
    public String adicionarProfessor(String nome, String telefone, String email) throws Exception {
        Professor professor = new Professor(nome, telefone, email);
        LISTA_DE_PROFESSORES.add(professor);
        return retornaMatriculaProfessorPeloObj(professor);
    }

    // Adiciona os professores com as disciplinas associadas (usuário mandou as disciplinas) -----------------------> OK
    public String adicionarProfessor(String nome, String telefone , String email, List<Disciplina> disciplinaDoProfessor) throws Exception {
        Professor professor = new Professor(nome, telefone, email, disciplinaDoProfessor);
        LISTA_DE_PROFESSORES.add(professor);
        return retornaMatriculaProfessorPeloObj(professor);
    }

    // Retorna a matricula do professor pelo objeto professor ------------------------------------------------------> Ok
    public String retornaMatriculaProfessorPeloObj(Professor professor) throws ProfessorNaoEncontradoException {
        for (Professor p : LISTA_DE_PROFESSORES) {
            if (p.equals(professor)) {
                return p.getMatricula();
            }
        }

        throw new ProfessorNaoEncontradoException("O professor não existe");
    }

    // Listar Professores (lista todos os professores da faculdade) ------------------------------------------------> Ok
    public String listarProfessores() throws ProfessorNaoEncontradoException{
        if (LISTA_DE_PROFESSORES.isEmpty()) {
            throw new ProfessorNaoEncontradoException("Lista de professores vazia");
        }

        String exibir = "";
        for (Professor p : LISTA_DE_PROFESSORES){
            exibir += p.toString() + "\n";
        }

        return exibir;
    }

    // Verifica se o professor existe na faculdade -----------------------------------------------------------------> OK
    public boolean existeProfessor(String matricula) throws ProfessorNaoEncontradoException{
        for ( Professor p : LISTA_DE_PROFESSORES){
            if(p.getMatricula().equalsIgnoreCase(matricula) && p.isAtivo()){
                return true;
            }
        }
        throw new ProfessorNaoEncontradoException("Professor não encontrado ou está desativado.");
    }

    // Busca o professor pela matricula e retorna ela se achar -----------------------------------------------------> Ok
    public Professor buscaProfessor(String matricula) throws ProfessorNaoEncontradoException {
        for(Professor p : LISTA_DE_PROFESSORES){
            if(p.getMatricula().equalsIgnoreCase(matricula)){
                return p;
            }
        }
        throw new ProfessorNaoEncontradoException("Professor não encontrado ou está desativado.");
    }

    // Outros métodos importantes
    public List<Professor> getListaProfessores() {
        return LISTA_DE_PROFESSORES;
    }
}