package model.servicos;

import model.exceptions.PessoaInvalidaException;
import model.exceptions.ProfessorNaoEncontradoException;
import model.servicos.GerenciamentoDeTurmas;
import model.pessoa.Professor;
import model.turma.Turma;
import java.util.ArrayList;
import java.util.List;

public class GerenciamentoDeProfessores {

    private final   List<Professor> listaProfessores = new ArrayList<>();
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

    private static void verificaTelefone(String telefone) throws PessoaInvalidaException {
        if(telefone == null || telefone.trim().isEmpty() ||  telefone.length() != 11){
            throw new PessoaInvalidaException("Telefone inválido, pois não deve se encontrar nulo ou vazio.");
        }
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