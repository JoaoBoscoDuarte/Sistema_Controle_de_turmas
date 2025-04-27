package model.main;

import model.disciplina.Disciplina;
import model.exceptions.*;
import model.faculdade.Faculdade;
import model.turma.Nota;
import model.turma.Turma;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public Faculdade faculdade;
    private Scanner sc;

    public Main() {
        faculdade = new Faculdade();
        this.sc = new Scanner(System.in);
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.executar();
    }

    public void executar() throws Exception {
        faculdade = faculdade.carregaControleDeTurmas();

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
                            "[7] Listar Alunos da faculdade\n" +
                            "[8] Listar Professores\n" +
                            "[9] Listar Disciplinas\n" +
                            "[10] Listar Turmas\n" +
                            "[11] Configurar Turma\n" +
                            "[12] Cadastrar notas\n" +
                            "[13] Inativar um aluno\n" +
                            "[14] Encerrar Turmas\n" +
                            "[15] Gerar relatório de turma\n" +
                            "[16] Gerar relatório da faculdade\n" +
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
                    System.err.println("Erro: Escolha aceita apenas números inteiros");
                    System.out.println(MENU_PRINCIPAL);
                }
            }

            // Escolhas (chamada das operações)
            switch (escolha) {
                case 0:
                    repetir = false;
                    faculdade.salvaControleDeTurmas();
                    break;

                case 1:
                    try {
                        cadastrarAluno();
                        System.out.println("Aluno cadastrado com sucesso!");
                    } catch (PessoaInvalidaException e) {
                        System.err.println("Falha ao cadastrar aluno: " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        cadastrarDisciplina();
                        System.out.println("Disciplina cadastrada com sucesso!");
                    } catch (DisciplinaJaCadastradaException | DisciplinaInvalidaException | CargaHorariaInvalidaException | NomeDaDisciplinaInvalidoException e) {
                        System.err.println("Falha ao cadastrar disciplina: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        cadastrarProfessor();
                        System.out.println("Professor cadastrado com sucesso!");
                    } catch (PessoaInvalidaException e) {
                        System.err.println("Falha ao tentar cadastrar professor: " + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        criarTurma();
                        System.out.println("Turma criada com sucesso!");
                    } catch (ProfessorNaoEncontradoException |DisciplinaNaoEncontradaException | IOException e) {
                        System.err.println("Falha ao criar turma: " + e.getMessage());
                    }
                    break;

                case 5:
                    try {
                        matricularAlunoEmTurma();
                        System.out.println("Aluno matriculado em turma com sucesso!");
                    } catch (TurmaInvalidaException | AlunoNaoEncontradoException | IOException e) {
                        System.err.println("Falha ao matricular aluno em turma: " + e.getMessage());
                    }
                    break;

                case 6:
                    listarAlunosDeUmaTurma();
                    break;

                case 7:
                    listarAlunosDaFaculdade();
                    break;

                case 8:
                    listarProfessores();
                    break;

                case 9:
                    listarDisciplinas();
                    break;

                case 10:
                    listarTurmas();
                    break;

                case 11:
                    configurarTurma();
                    break;

                case 12:
                    try {
                        cadastrarNotas();
                        System.out.println("Notas cadastradas com suceso!");
                    } catch (AlunoNaoEncontradoException | TurmaInvalidaException e) {
                        System.err.println("Falha ao cadastrar nota: " + e.getMessage());
                    }
                    break;

                case 13:
                    try {
                        inativarAluno();
                        System.out.println("Aluno desativado com sucesso!");
                    } catch (AlunoNaoEncontradoException e) {
                        System.err.println("Falha ao desativar aluno: " + e.getMessage());
                    }
                    break;

                case 14:
                    encerrarTurmas();
                    break;

                case 15:
                    gerarRelatorioDeTurma();
                    break;

                case 16:
                    try {
                        gerarRelatorioDaFaculdade();
                    } catch (IOException e) {
                        System.err.println("Erro ao gerar relatório da faculdade: " + e.getMessage());
                    }
                    break;

                case 17:
                    calcularNotaFinalAlunos();
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.\n");
                    break;
            }
        }
    }

    // Método de cadastro aluno --------------------------------------------------->  Falta o tratamento de exceções
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
        faculdade.salvaControleDeTurmas();
    }

    // Método de cadastro da disciplina -------------------------------------------> Falta o tratamento de exceções
    public void cadastrarDisciplina() throws DisciplinaJaCadastradaException, IOException, NomeDaDisciplinaInvalidoException, CargaHorariaInvalidaException, DisciplinaInvalidaException {
        // Recebendo os dados da disciplina e cadastrando
        System.out.println("Informe o nome da disciplina: ");
        String disciplina = sc.nextLine();

        System.out.println("Informe o codigo para a disciplina: ");
        String codigo = sc.nextLine();

        System.out.println("Informe a carga horária: ");
        int cargaHoraria = sc.nextInt();
        sc.nextLine();

        faculdade.cadastrarDisciplina(disciplina, codigo ,cargaHoraria);
        faculdade.salvaControleDeTurmas();
    }

    // Método de cadastro de professor ------------------------------------------->  Falta o tratamento de exceções
    public void cadastrarProfessor() throws Exception {
        List<Disciplina> disciplinasDoProfessor = new ArrayList<>();
        // Recebendo dados do professor e cadastrando no sistema
        System.out.println("Informe o nome do professor: ");
        String nome = sc.nextLine();

        // Aqui o usuário pode escolher se quer passar todas as disciplinas do professor na criação (conforme o caso de uso)
        System.out.println("Gostaria de adicionar as disciplinas do professor agora? [s]/[n]");
        String escolha = sc.nextLine().toLowerCase();

        boolean comDisciplinas = false;
        if (escolha.equals("s")) {
            try {
                System.out.println("Quantidade de disciplinas que deseja adicionar: ");
                int quantidade = sc.nextInt();
                sc.nextLine();

                for (int i = 0; i < quantidade; i++) {
                    System.out.println("Insira o nome da disciplina: ");
                    String nomeDisciplina = sc.nextLine();

                    if (faculdade.existeDisciplina(nomeDisciplina)) {
                        disciplinasDoProfessor.add(faculdade.buscaDisciplina(nomeDisciplina));
                    }
                }

                comDisciplinas = true;
            } catch (DisciplinaNaoEncontradaException e) {
                throw new DisciplinaNaoEncontradaException("Disciplina não existe");
            }
        }

        System.out.println("Informe o telefone do professor: ");
        String telefone = sc.nextLine();

        System.out.println("Informe o e-mail do professor: ");
        String email = sc.nextLine();

        if (comDisciplinas) {
            faculdade.adicionarProfessor(nome, telefone, email, disciplinasDoProfessor);

        } else {
            faculdade.adicionarProfessor(nome, telefone, email);
            System.out.println("Professor cadastrado.");
        }

        faculdade.salvaControleDeTurmas();
    }

    // Método para criar uma turma ------------------------------------------->  Falta o tratamento de exceções
    public void criarTurma() throws ProfessorNaoEncontradoException, DisciplinaNaoEncontradaException, IOException, DisciplinaInvalidaException {
        System.out.println("Informe o nome da disciplina: ");
        String nomeDisciplina = sc.nextLine();

        System.out.println("Informe o id do professor: ");
        String idProfessor = sc.nextLine();

        faculdade.criarTurma(nomeDisciplina, idProfessor);
        System.out.println("Turma criada com sucesso.");

        faculdade.salvaControleDeTurmas();
    }

    // Método para matricular um aluno a uma turma ------------------------------------------->  Falta o tratamento de exceções
    public void matricularAlunoEmTurma() throws TurmaInvalidaException, AlunoNaoEncontradoException, IOException {
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

        faculdade.salvaControleDeTurmas();
    }

    // Método de listagem de alunos de uma turma ------------------------------------------->  Falta o tratamento de exceções
    public void listarAlunosDeUmaTurma() {
        System.out.println("Alunos da turma: \n" + faculdade.listarAlunos());
    }

    // Método para listar os alunso da faculdade -------------------------------------------> Falta o tratamento de exceções
    public void listarAlunosDaFaculdade() {
        System.out.println("Alunos da faculdade: \n" + faculdade.listarAlunosDaFaculdade());
    }

    //Método para listar os professores da faculdade -------------------------------------------> Falta o tratamento de exceções
    public void listarProfessores() {
        System.out.println("Professores da faculdade: " + faculdade.listarProfessores());
    }

    // Método para listar as disciplinas da faculdade -------------------------------------------> Falta o tratamento de exceções
    public void listarDisciplinas() {
        System.out.println("Disciplinas: " + faculdade.listarDisciplinas());
    }

    // Método para listar todas as turmas da faculdade -------------------------------------------> Falta o tratamento de exceções
    public void listarTurmas() {
        System.out.println("Turmas da faculdade: " + faculdade.listarTurmas());
    }


    public void configurarTurma() {
    }

    // Cadastra a nota dos alunos ---------------------------------------------------->
    public void cadastrarNotas() throws AlunoNaoEncontradoException, TurmaInvalidaException {
        System.out.println("Informe o código da turma: ");
        String codigo = sc.nextLine();

        System.out.println("Informe a unidade avaliativa: ");
        int unidade = sc.nextInt();

        // Usando a fachada para buscar a turma
        Turma turma = faculdade.buscarTurma(codigo);

        // Verifica se a turma tem alunos
        if (turma.getNotasAluno().isEmpty()) {
            System.out.println("Não há alunos matriculados nesta turma.");
            return;
        }

        // Para cada aluno da turma, solicita a nota para a unidade
        for (Nota nota : turma.getNotasAluno()) {
            System.out.println("Informe a nota do aluno " + nota.getMatricula() + " para a unidade " + unidade + ": ");
            double notaAluno = sc.nextDouble();
            try {
                faculdade.cadastrarNotasUnidade(codigo, unidade, notaAluno);

            } catch (IntervaloDeNotaException | IntervaloDeUnidadeException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    // Método para inativar aluno da turma --------------------------------------------->
    public void inativarAluno() throws AlunoNaoEncontradoException {
        System.out.println("Informe a matrícula do aluno: ");
        String matricula = sc.nextLine();

        if (matricula.trim().isEmpty()) {
            System.out.println("Inválido. A matrícula do aluno não pode estar vazia.");
            return;
        }

        faculdade.desativaAluno(matricula);
    }

    public void calcularNotaFinalAlunos() {
        System.out.println("Insira o código da turma que deseja calcular a nota dos alunos: ");
        String codigo = sc.nextLine();

//        try {
//            if (faculdade.buscarTurma(codigo);
//        }
    }

    // Método que gera os relatŕoios da turma ------------------------------------------->  Falta o tratamento de exceções
    public void gerarRelatorioDeTurma() throws TurmaInvalidaException, IOException {
        System.out.println("Insira o código da turma que deseja gerar o relatório: ");
        String codigo = sc.nextLine();

        faculdade.gerarRelatorioDaTurma(codigo);
        System.out.println("Relatorio gerado com sucesso!\n");
    }

    // Método que gera os relatŕoios da turma ------------------------------------------->  Falta o tratamento de exceções
    public void gerarRelatorioDaFaculdade() throws IOException {
        faculdade.gerarRelatorioDaFaculdade();
        System.out.println("Relatório da turma gerado com sucesso!\n");
    }

    public void encerrarTurmas() {
    }
}