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

    public void adicionarProfessor(String nome, String telefone , String email) throws Exception {
        Professor professor = new Professor(nome, telefone, email, turmas);
        listaProfessores.add(professor);
    }

    public String listarProfessores(){
        String listaDeProfessores = "";
        for (Professor p : listaProfessores){
            listaDeProfessores += p + "\n";
        }
        return listaDeProfessores;
    }

    public boolean existeProfessor(String matricula) throws ProfessorNaoEncontradoException{
        for ( Professor p : listaProfessores){
            if(p.getMatricula().equalsIgnoreCase(matricula) && p.isAtivo()){
                return true;
            }
        }
        throw new ProfessorNaoEncontradoException("Professor não encontrado ou se encontra desativado.");
    }


    public String buscaProfessor(String matricula)throws ProfessorNaoEncontradoException {
        for(Professor p : listaProfessores){
            if(p.getMatricula().equalsIgnoreCase(matricula)){
                return p.toString();
            }
        }
        throw new ProfessorNaoEncontradoException("Professor não encontrado ou se econtra desativo.");
    }
    
    private static void verificaListaDeTurmas(List<Turma> turmas) throws Exception {
        if(turmas.isEmpty()){
            throw new Exception("A lista de turmas não pode se encontar vazia");
        }
    }
    public Professor buscarProfessorPorMatricula(String matricula) throws ProfessorNaoEncontradoException {
        Objects.requireNonNull(matricula, "Matrícula do professor não pode ser nula.");
        for (Professor p : listaProfessores) {
            if (p.getMatricula().equalsIgnoreCase(matricula) && p.isAtivo()) {
                return p;
            }
        }
        throw new ProfessorNaoEncontradoException("Professor com matrícula '" + matricula + "' não encontrado ou está desativado.");
    }

    public List<Professor> getListaProfessores() {
        return listaProfessores;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }
}