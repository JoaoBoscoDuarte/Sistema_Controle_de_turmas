package model.servicos;

import model.pessoa.Professor;
import model.turma.Turma;

import java.util.ArrayList;
import java.util.List;

public class GerenciamentoDeProfessores {

    private List<Professor> listaProfessores = new ArrayList<>();

    public void adicionarProfessor(Professor professor){
        listaProfessores.add(professor);
    }

    public void adicionarProfessor(String nome, String telefone , String email, List<Turma> turmas) throws Exception {
        Professor professor = new Professor(nome, telefone, email, turmas);
        listaProfessores.add(professor);
    }

    public String listarProfessores(){
        String lista = "";
        for (Professor p : listaProfessores){
            lista += p + "\n";
        }
        return lista;
    }

    public boolean verificaExistenciaDoProfessor(String nome){
        for (Professor p : listaProfessores){
            if (p.getNome().contains(nome)){
                return true;
            }
        }
        return false;
    }
}
