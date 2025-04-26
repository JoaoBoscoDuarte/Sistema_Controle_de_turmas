package model.main;

import model.exceptions.*;
import model.faculdade.Faculdade;

import java.io.IOException;
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
            final String MENU_PRINCIPAL =
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
                            "[10] Gerar relatório de turma\n" +
                            "[11] Encerrar Turmas\n" +
                            "[0] Sair\n";

            System.out.println(MENU_PRINCIPAL);

            // Verifica e evita entrada inválida (Aceita apenas números inteiros)
            int escolha = 0;
            boolean entradaValida = false;

            while (!entradaValida) {
                String entrada = sc.nextLine();

                try {
                    escolha = Integer.parseInt(entrada);
                    entradaValida = true;

                } catch (NumberFormatException e) {
                    System.out.println("Erro: Escolha aceita apenas números inteiros");
                    System.out.println(MENU_PRINCIPAL);
                }
            }

            // Escolhas (chamada das operações)
            switch (escolha) {
                case 0:
                    repetir = false;
                    break;

                case 1:
                    //exceçoes cadastro aluno
                    try {
                        cadastrarAluno();
                    } catch (PessoaInvalidaException e) {
                        System.out.println("Falha ao cadastrar aluno: " +e.getMessage());
                    }
                    break;

                case 2:
                    //Ajustes nas exceções para cadastrar disciplina
                    try {
                        cadastrarDisciplina();
                    } catch (DisciplinaJaCadastradaException e){
                        System.out.println("Falha ao cadastrar disciplina: " + e.getMessage());
                    } catch (DisciplinaInvalidaException e) {
                        System.out.println("Falha no nome da disciplina: " +e.getMessage());
                    } catch (CargaHorariaInvalidaException e) {
                        System.out.println("Falha quanto a carga horária informada: " +e.getMessage());
                    }
                    break;

                case 3:
                    // Tratanado exceções referentes ao cadastramento de professor
                    try {
                        cadastrarProfessor();
                    } catch (PessoaInvalidaException e){
                        System.out.println("Falha ao tentar cadastrar professor: " + e.getMessage());
                    }
                    break;

                case 4:
                    //Recebendo dados e criando turma, fazendo tratamento de exceções
                    try {
                        criarTurma();
                    } catch (Exception e){
                        System.out.println("Falha ao criar turma: " + e.getMessage());
                    }
                    break;

                case 5:
                    // Tratando exceções ao matricular aluno em turma
                    try {
                        matricularAlunoEmTurma();
                    } catch (TurmaInvalidaException e) {
                        System.out.println("Falha ao matricular aluno em turma: " +e.getMessage());
                    } catch (AlunoNaoEncontradoException e) {
                        System.out.println("Falha ao tentar matricular aluno em turma: " +e.getMessage());
                    }
                    break;

                case 6:
                    listarAlunosDeUmaTurma();
                    break;

                case 7:
                    configurarTurma();
                    break;

                case 8:
                    // Tratando exceções referente ao cadastramento de notas
                    try {
                        cadastrarNotas();
                    } catch (AlunoNaoEncontradoException e){
                        System.out.println("Falha ao cadastrar nota: " + e.getMessage());
                    }
                    break;

                case 9:
                    // Tratando exceções da inativação de alunos
                    try {
                        inativarAluno();
                    } catch (AlunoNaoEncontradoException e) {
                        System.out.println("Falha ao desativar aluno: " +e.getMessage());
                    }
                    break;

                case 10:
                    gerarRelatorioDeTurma();
                    break;

                case 11:
                    encerrarTurmas();
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.\n");
                    break;
            }
        }
    }

    public void cadastrarAluno() throws Exception {
        //Recebendo dados do aluno e cadastrando no sistema
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

    public void cadastrarDisciplina() throws DisciplinaJaCadastradaException {
        System.out.println("Informe o nome da disciplina: ");
        String disciplina = sc.nextLine();
        System.out.println("Informe a carga horária: ");
        int cargaHoraria = sc.nextInt();

        faculdade.cadastrarDisciplina(disciplina,cargaHoraria);
        System.out.println("Disciplina cadastrada.");
    }

    public void cadastrarProfessor() throws Exception {
        // Recebendo dados do professor e cadastrando no sistema

        System.out.println("Informe o nome do professor: ");
        String nome = sc.nextLine();
        System.out.println("Informe o telefone do professor: ");
        String telefone = sc.nextLine();
        System.out.println("Informe o e-mail do professor: ");
        String email = sc.nextLine();
        faculdade.adicionarProfessor(nome, telefone, email);
        System.out.println("Professor cadastrado.");
    }

    public void criarTurma() throws ProfessorNaoEncontradoException, DisciplinaNaoEncontradaException {
        System.out.println("Informe o nome da disciplina: ");
        String nomeDisciplina = sc.nextLine();
        System.out.println("Informe o id do professor: ");
        String idProfessor = sc.nextLine();
        faculdade.criarTurma(nomeDisciplina, idProfessor);
        System.out.println("Turma criada com sucesso.");
    }

    public void matricularAlunoEmTurma() throws TurmaInvalidaException, AlunoNaoEncontradoException {
        System.out.println("Informe a matrícula do aluno: ");
        String matricula = sc.nextLine();

        if (matricula.trim().isEmpty()) {
            System.out.println("Inválido. O número da matrícula do aluno não pode estar vazia.");
            return;
        }

        System.out.println("Informe o código da turma: ");
        String codigo = sc.nextLine();

        if (codigo.trim().isEmpty()) {
            System.out.println("Inválido. O código da turma não pode estar vazio.");
            return;
        }

        faculdade.adicionarAlunoATurma(matricula, codigo);
        System.out.println("Aluno matriculado a turma.");
    }

    public void listarAlunosDeUmaTurma() {
        //verificar se esse metodo esta correto
        System.out.println("Alunos da turma: \n" + faculdade.listarAlunos());
    }

    public void configurarTurma() {
    }

    public void cadastrarNotas() throws AlunoNaoEncontradoException, TurmaInvalidaException {
        //Ver a quetão da quantidade de unidades avaliativas

        System.out.println("Informe o código da disciplina: ");
        String codigo = sc.nextLine();
        System.out.println("Informe a unidade avaliativa: ");
        int unidade = sc.nextInt();
        System.out.println("Informe a nota: ");
        double nota = sc.nextDouble();
        System.out.println("Informe a matricula do aluno: ");
        String matricula = sc.nextLine();
        faculdade.cadastrarNotasUnidade(codigo, unidade, nota, matricula);
        System.out.println("Nota cadastrada.");
    }

    public void inativarAluno() throws AlunoNaoEncontradoException {
        System.out.println("Informe a matrícula do aluno: ");
        String matricula = sc.nextLine();

        if (matricula.trim().isEmpty()) {
            System.out.println("Inválido. A matrícula do aluno não pode estar vazia.");
            return;
        }

        faculdade.desativaAluno(matricula);
        System.out.println("Aluno desativado.");
    }

    public void gerarRelatorioDeTurma() throws IOException {
        faculdade.gerarRelatorioDaTurma();
        System.out.println("Relatório da turma gerado com sucesso!\n");
    }

    public void encerrarTurmas() {
    }
}