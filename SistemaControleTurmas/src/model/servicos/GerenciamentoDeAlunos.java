package model.servicos;

import model.exceptions.PessoaInvalidaException;
import model.pessoa.Aluno;
import model.exceptions.AlunoNaoEncontradoException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*  ================| Só altere em caso de urgência! |====================
 *  ----------------------Classe 100% concluída-----------------------> OK
 */

public class GerenciamentoDeAlunos implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<Aluno> listaAlunos = new ArrayList<>();

    public List<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    public void adicionaAluno(String nome, String telefone, String email, String curso) throws PessoaInvalidaException {
        Aluno aluno = new Aluno(nome, telefone, email, curso);
        listaAlunos.add(aluno);
    }

    public String listarAlunos() {
        StringBuilder lista = new StringBuilder();
        for (Aluno aluno : listaAlunos) {
            lista.append(aluno).append("\n");
        }
        return lista.toString();
    }

    public String consultaDadosAluno(String matricula) throws AlunoNaoEncontradoException {
        for (Aluno aluno : listaAlunos) {
            if (aluno.getMatricula().equalsIgnoreCase(matricula) && aluno.isAtivo()) {
                return aluno.toString();
            }
        }
        throw new AlunoNaoEncontradoException("Aluno não encontrado ou não ativo.");
    }

    public boolean existeAluno(String matricula) {
        for (Aluno aluno : listaAlunos) {
            if (aluno.getMatricula().equalsIgnoreCase(matricula) && aluno.isAtivo()) {
                return true;
            }
        }
        return false;
    }

    public void desativaAluno (String matricula) throws AlunoNaoEncontradoException {
        for (Aluno aluno : listaAlunos) {
            if (aluno.getMatricula().equalsIgnoreCase(matricula) && aluno.isAtivo()) {
                aluno.invalidar();
                return;
            }
        }
        throw new AlunoNaoEncontradoException("Aluno não encontrado ou não ativo.");
    }

    public Aluno buscaAluno(String matricula) throws AlunoNaoEncontradoException{
        //ajuste na verificação da matricula e atividade do aluno, adição de exception
        for (Aluno a : listaAlunos) {
            if (a.getMatricula().equalsIgnoreCase(matricula) && a.isAtivo()) {
                return a;
            }
        }
        throw new AlunoNaoEncontradoException("Aluno não encontrado ou não ativo.");
    }
}