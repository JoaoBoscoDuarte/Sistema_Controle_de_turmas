package model.servicos;

import model.pessoa.Professor;
import model.turma.Turma;
import java.util.ArrayList;
import java.util.List;

public class GerenciamentoDeProfessores {

    private List<Professor> listaProfessores = new ArrayList<>();


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

    public boolean existeProfessor(String nome) throws Exception{
        verificaNome(nome);
        for (Professor p : listaProfessores){
            if (p.getNome().contains(nome)){
                return true;
            }
        }
        return false;
    }

    private static void verificaTelefone(String telefone) throws Exception {
        if(telefone == null || telefone.trim().isEmpty()){
            throw new Exception("Telefone não pode se encontrar nulo ou vazio.");
        }
    }

    private static void verificaListaDeTurmas(List<Turma> turmas) throws Exception {
        if(turmas.isEmpty()){
            throw new Exception("A lista de turmas não pode se encontar vazia");
        }
    }

    private static void verificaEmail(String email) throws Exception {
        if(email == null || email.trim().isEmpty()){
            throw new Exception("E-mail não pode se encontrar nulo ou vazio");
        }
    }

    private static void verificaNome(String nome) throws Exception {
        if(nome == null || nome.trim().isEmpty()){
            throw new Exception("Nome não pode se encontrar nulo ou vazio.");
        }
    }
}