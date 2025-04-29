package model.servicos;

import model.exceptions.PessoaInvalidaException;
import model.pessoa.Aluno;
import model.exceptions.AlunoNaoEncontradoException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*  ================| Só altere em caso de urgência! |====================
 *  ----------------------Classe 100% concluída-------------------------> OK
 */

public class GerenciamentoDeAlunos implements Serializable {
    private final List<Aluno> listaAlunos = new ArrayList<>();

    public List<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    // Adiciona aluno a lista de alunos (cria um aluno) ------------------------------------------------------------> OK
    public void adicionaAluno(String nome, String telefone, String email, String curso) throws PessoaInvalidaException {
        Aluno aluno = new Aluno(nome, telefone, email, curso);
        listaAlunos.add(aluno);
    }

    // Consulta os dados do aluno (aluno.toString()) ---------------------------------------------------------------> OK
    public String consultaDadosAluno(String matricula) throws AlunoNaoEncontradoException {
        return buscaAluno(matricula).toString();
    }

    // Desativa o aluno (remoção lógica da lista de alunos) --------------------------------------------------------> OK
    public void desativaAluno(String matricula) throws AlunoNaoEncontradoException {
        for (Aluno aluno : listaAlunos) {
            if (aluno.getMatricula().equalsIgnoreCase(matricula) && aluno.isAtivo()) {
                aluno.setAtivo(false);
            }
        }
        throw new AlunoNaoEncontradoException("Aluno não encontrado ou não ativo.");
    }

    // Busca o aluno a partir da matrícula (retorna o aluno a partir da matricula) ---------------------------------> OK
    public Aluno buscaAluno(String matricula) throws AlunoNaoEncontradoException{
        //ajuste na verificação da matricula e atividade do aluno, adição de exception
        for (Aluno a : listaAlunos) {
            if (a.getMatricula().equalsIgnoreCase(matricula) && a.isAtivo()) {
                return a;
            }
        }
        throw new AlunoNaoEncontradoException("Aluno não encontrado ou não ativo.");
    }

    // Listar Alunos de toda a faculdade ---------------------------------------------------------------------------> OK
    public String listarAlunosDaFaculdade() throws AlunoNaoEncontradoException {
        if (listaAlunos.isEmpty()) {
            throw new AlunoNaoEncontradoException("A lista de alunos está vazia");
        }

        String exibir = "";
        for (Aluno a : listaAlunos) {
            exibir += a.toString() + "\n";
        }

        return exibir;
    }

    // Retorna o nome do aluno a partir da matrícula
    public String retornaNomeAluno(String matricula) throws AlunoNaoEncontradoException {
        return buscaAluno(matricula).toString();
    }
}