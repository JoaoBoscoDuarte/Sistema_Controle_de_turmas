package model.main;

import model.exceptions.AlunoNaoEncontradoException;
import model.exceptions.PessoaInvalidaException;
import model.faculdade.Faculdade;

import java.util.Scanner;

public class Main {
    public Faculdade faculdade;
    private Scanner sc;

    public Main() {
        this.faculdade = new Faculdade();
        this.sc = new Scanner(System.in);
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.executar();
    }

    public void executar() throws Exception {

        boolean repetir = true;
        while (repetir) {
            final String menuPrincipal =
                    "===========| Sistema de controle de turmas |===========\n" +
                            "[1] Cadastrar Aluno\n" +
                            "[2] Cadastrar Disciplina\n" +
                            "[3] Cadastrar Professor\n" +
                            "[4] Criar Turma\n" +
                            "[5] Matricular Aluno em uma Turma\n" +
                            "[6] Listar Alunos de uma Turma\n" +
                            "[7] Configurar Turma\n" +
                            "[8] Cadastrar notas\n" +
                            "[9] Inativar um aluno\n" +
                            "[10] Inativar um aluno\n" +
                            "[11] Gerar relatório de turma\n" +
                            "[12] Encerrar Turmas\n" +
                            "[0] Sair\n";

            System.out.println(menuPrincipal);
            int escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 0:
                    repetir = false;
                    break;

                case 1:
                    try {
                        cadastrarAluno();
                    } catch (PessoaInvalidaException e) {
                        System.out.println("Erro ao tentar cadastrar aluno: " +e.getMessage());
                    }
                    break;

                case 2:
                    cadastrarDisciplina();
                    break;

                case 3:
                    cadastrarProfessor();
                    break;

                case 4:
                    criarTurma();
                    break;

                case 5:
                    matricularAlunoEmTurma();
                    break;

                case 6:
                    listarAlunosDeUmaTurma();
                    break;

                case 7:
                    configurarTurma();
                    break;

                case 8:
                    cadastrarNotas();
                    break;

                case 9:
                    try {
                        inativarAluno();
                    } catch (AlunoNaoEncontradoException e) {
                        System.out.println("Erro ao tentar desativar aluno: " +e.getMessage());
                    }
                    break;

                case 10:
                    inativarAluno(); // Duplicado, como comentado antes — pode ajustar se for outro caso
                    //é complicado
                    break;

                case 11:
                    gerarRelatorioDeTurma();
                    break;

                case 12:
                    encerrarTurmas();
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    public void cadastrarAluno() throws Exception {
        System.out.println("Informe o nome do aluno: ");
        String nome = sc.nextLine();
        System.out.println("Informe o telefone do aluno: ");
        String telefone = sc.nextLine();
        System.out.println("Informe o e-mail do aluno: ");
        String email = sc.nextLine();
        System.out.println("Informe o curso do aluno: ");
        String curso = sc.nextLine();

        faculdade.adicionarAluno(nome, telefone, email, curso);
        System.out.println("Aluno cadastrado.");
    }

    public void cadastrarDisciplina() {
    }

    public void cadastrarProfessor() {
    }

    public void criarTurma() {
    }

    public void matricularAlunoEmTurma() {
    }

    public void listarAlunosDeUmaTurma() {
    }

    public void configurarTurma() {
    }

    public void cadastrarNotas() {
    }

    public void inativarAluno() throws AlunoNaoEncontradoException {
        System.out.println("Informe a matrícula do aluno: ");
        String matricula = sc.nextLine();

        boolean alunoEstaAtivo = faculdade.existeAluno(matricula);

        if (alunoEstaAtivo) {
            faculdade.desativaAluno(matricula);
            System.out.println("Aluno desativado.");
        } else {
            System.out.println("Aluno não encontrado ou já inativo.");
        }
    }

    public void gerarRelatorioDeTurma() {
    }

    public void encerrarTurmas() {
    }
}