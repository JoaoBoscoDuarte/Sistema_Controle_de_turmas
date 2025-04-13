package model.servicos;

import java.util.ArrayList;
import java.util.List;

public class GerenciamentoDeAlunos {
    private final List<Aluno> alunos = new ArrayList<>();

    public void adicionaAluno(Aluno aluno) {
        aluno.validar();
        alunos.add(aluno);
    }

    public void adicionaAluno(String nome, String telefone, String email) throws Exception {
        Aluno aluno = new Aluno(nome, telefone, email);
        aluno.validar();
        alunos.add(aluno);
    }

    public String listaAlunos() {
        StringBuilder lista = new StringBuilder();
        for (Aluno aluno : alunos) {
            lista.append(aluno).append("\n");
        }
        return lista.toString();
    }

    public String consultaDadosAluno(String matricula) throws AlunoNaoEncontradoException{
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equalsIgnoreCase(matricula)) {
                return aluno.toString();
            }
        }
        throw new AlunoNaoEncontradoException("Aluno não encontrado ou não ativo.");
    }

    public boolean existeAluno(String matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equalsIgnoreCase(matricula) && aluno.isAtivo()) {
                return true;
            }
        }
        return false;
    }

    public void desativaAluno (String matricula) throws AlunoNaoEncontradoException {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equalsIgnoreCase(matricula) && aluno.isAtivo()) {
                aluno.setAtivo(false);
                return;
            }
        }
        throw new AlunoNaoEncontradoException("Aluno não encontrado ou não ativo.");
    }
}