package model.servicos;

import model.exceptions.ProfessorNaoEncontradoException;
import model.pessoa.Professor;
import java.util.ArrayList;
import java.util.List;

/*  ================| Só altere em caso de urgência! |====================
 *  ----------------------Classe 100% concluída-----------------------> OK
 */

public class GerenciamentoDeProfessores {
    private final List<Professor> LISTA_DE_PROFESSORES = new ArrayList<>();

    // Adiciona professor
    public void adicionarProfessor(String nome, String telefone , String email) throws Exception {
        Professor professor = new Professor(nome, telefone, email);
        LISTA_DE_PROFESSORES.add(professor);
    }

    // Listar Professores
    public StringBuilder listarProfessores() {
        StringBuilder exibir = new StringBuilder();
        for (Professor p : LISTA_DE_PROFESSORES){
            exibir.append(p).append("\n");
        }
        return exibir;
    }

    public boolean existeProfessor(String matricula) throws ProfessorNaoEncontradoException{
        for ( Professor p : LISTA_DE_PROFESSORES){
            if(p.getMatricula().equalsIgnoreCase(matricula) && p.isAtivo()){
                return true;
            }
        }
        throw new ProfessorNaoEncontradoException("Professor não encontrado ou está desativado.");
    }

    public Professor buscaProfessor(String matricula) throws ProfessorNaoEncontradoException {
        for(Professor p : LISTA_DE_PROFESSORES){
            if(p.getMatricula().equalsIgnoreCase(matricula)){
                return p;
            }
        }
        throw new ProfessorNaoEncontradoException("Professor não encontrado ou está desativado.");
    }

    public List<Professor> getListaProfessores() {
        return LISTA_DE_PROFESSORES;
    }
}