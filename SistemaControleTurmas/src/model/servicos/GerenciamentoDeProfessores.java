package model.servicos;

import model.exceptions.PessoaInvalidaException;
import model.pessoa.Professor;
import model.turma.Turma;
import java.util.ArrayList;
import java.util.List;

public class GerenciamentoDeProfessores {

    private final List<Professor> listaProfessores = new ArrayList<>();

    public void adicionarProfessor(String nome, String telefone , String email, List<Turma> turmas) throws Exception {
        verificaNome(nome);
        verificaTelefone(telefone);
        verificaEmail(email);
        verificaListaDeTurmas(turmas);
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

    public boolean existeProfessor(Professor professor) {
        return listaProfessores.contains(professor);
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

    private static void verificaEmail(String email) throws PessoaInvalidaException {
        if(email == null || email.trim().isEmpty()){
            throw new PessoaInvalidaException("E-mail inválido, pois não deve se encontrar nulo ou vazio");
        }
    }

    private static void verificaNome(String nome) throws PessoaInvalidaException {
        if(nome == null || nome.trim().isEmpty()){
            throw new PessoaInvalidaException("Nome inválido, pois não deve se encontrar nulo ou vazio.");
        }
    }

    public List<Professor> getListaProfessores() {
        return listaProfessores;
    }
}