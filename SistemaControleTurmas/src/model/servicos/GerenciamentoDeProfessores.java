package model.servicos;

import model.exceptions.PessoaInvalidaException;
import model.exceptions.ProfessorNaoEncontradoException;
import model.servicos.GerenciamentoDeTurmas;
import model.pessoa.Professor;
import model.turma.Turma;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GerenciamentoDeProfessores {

    private List<Professor> listaProfessores = new ArrayList<>();
    private GerenciamentoDeTurmas gerenciamentoDeTurmas;
    List<Turma> turmas = gerenciamentoDeTurmas.getTurmas();

    // Adiciona professor
    public void adicionarProfessor(String nome, String telefone , String email) throws Exception {
        Professor professor = new Professor(nome, telefone, email);
        listaProfessores.add(professor);
    }

    // Listar Professores
    public StringBuilder listarProfessores() {
        StringBuilder exibir = new StringBuilder();
        for (Professor p : listaProfessores){
            exibir.append(p + "\n");
        }
        return exibir;
    }

    public boolean existeProfessor(String matricula) throws ProfessorNaoEncontradoException{
        for ( Professor p : listaProfessores){
            if(p.getMatricula().equalsIgnoreCase(matricula) && p.isAtivo()){
                return true;
            }
        }
        throw new ProfessorNaoEncontradoException("Professor não encontrado ou se encontra desativado.");
    }

    public Professor buscaProfessor(String matricula) throws ProfessorNaoEncontradoException {
        for(Professor p : listaProfessores){
            if(p.getMatricula().equalsIgnoreCase(matricula)){
                return p;
            }
        }
        throw new ProfessorNaoEncontradoException("Professor não encontrado ou se encontra desativo.");
    }
    
    private static void verificaListaDeTurmas(List<Turma> turmas) throws Exception {
        if(turmas.isEmpty()){
            throw new Exception("A lista de turmas não pode se encontar vazia");
        }
    }

    public List<Professor> getListaProfessores() {
        return listaProfessores;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }
}