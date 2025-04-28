package model.main;

import model.disciplina.Disciplina;
import model.exceptions.*;
import model.faculdade.Faculdade;
import model.pessoa.Professor;
import model.turma.Nota;
import model.turma.Turma;
import model.turma.media.MediaDescartaMenorNota;
import model.turma.media.MediaSimples;
import model.turma.media.MediaUltimaValeMais;
import model.turma.media.TiposDeMediaIF;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public Faculdade faculdade;
    private final Scanner sc;

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
                            "[1] Cadastrar Aluno\n" +                    //OK
                            "[2] Cadastrar Disciplina\n" +               //OK
                            "[3] Cadastrar Professor\n" +                //OK
                            "[4] Criar Turma\n" +                        //OK
                            "[5] Matricular Aluno em uma Turma\n" +      //OK
                            "[6] Listar Alunos de uma Turma\n" +         //OK
                            "[7] Listar Alunos da faculdade\n" +         //OK
                            "[8] Listar Professores\n" +                 //OK
                            "[9] Listar Disciplinas\n" +                 //OK
                            "[10] Listar Turmas\n" +                     //OK
                            "[11] Configurar Turma\n" +
                            "[12] Cadastrar notas\n" +
                            "[13] Inativar um aluno\n" +
                            "[14] Encerrar Turmas\n" +
                            "[15] Gerar relatório de turma\n" +
                            "[16] Gerar relatório da faculdade\n" +      //OK
                            "[17] Calcular nota final do aluno\n" +
                            "[0] Sair\n";                                //OK

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

            boolean tentarNovamente = true;

            // Escolhas (chamada das operações)
            switch (escolha) {
                case 0:
                    repetir = false;
                    faculdade.salvaControleDeTurmas();
                    break;

                case 1:
                    while (tentarNovamente) {
                        try {
                            cadastrarAluno();
                            System.out.println("Aluno cadastrado com sucesso!");
                            tentarNovamente = false;

                        } catch (PessoaInvalidaException e) {
                            System.out.println("Falha ao cadastrar aluno: " + e.getMessage());
                            System.out.println("Deseja tentar novamente? [s]/[n]");
                            String opcao = sc.nextLine();

                            if (!opcao.equalsIgnoreCase("s")) {
                                tentarNovamente = false;
                            }
                        }
                    }
                    break;

                case 2:
                    while (tentarNovamente) {
                        try {
                            cadastrarDisciplina();
                            System.out.println("Disciplina cadastrada com sucesso!");
                            tentarNovamente = false;

                        } catch (DisciplinaJaCadastradaException | DisciplinaInvalidaException | CargaHorariaInvalidaException | NomeDaDisciplinaInvalidoException e) {
                            System.err.println("Falha ao cadastrar disciplina: " + e.getMessage());
                            System.out.println("Deseja tentar novamente? [s]/[n]");
                            String opcao = sc.nextLine();

                            if (!opcao.equalsIgnoreCase("s")) {
                                tentarNovamente = false;
                            }
                        }
                    }
                    break;

                case 3:
                    while (tentarNovamente) {
                        try {
                            cadastrarProfessor();
                            tentarNovamente = false;

                        } catch (PessoaInvalidaException e) {
                            System.err.println("Falha ao cadastrar professor: " + e.getMessage());
                            System.out.println("Deseja tentar novamente? [s]/[n]");
                            String opcao = sc.nextLine();

                            if (!opcao.equalsIgnoreCase("s")) {
                                tentarNovamente = false;
                            }
                        }
                    }
                    break;

                case 4:
                    while (tentarNovamente) {
                        try {
                            criarTurma();
                            tentarNovamente = false;

                        } catch (ProfessorNaoEncontradoException |DisciplinaNaoEncontradaException | IOException e) {
                            System.err.println("Falha ao criar turma: " + e.getMessage());
                            System.out.println("Deseja tentar novamente? [s]/[n]");
                            String opcao = sc.nextLine();

                            if (!opcao.equalsIgnoreCase("s")) {
                                tentarNovamente = false;
                            }
                        }
                    }
                    break;

                case 5:
                    while (tentarNovamente) {
                        try {
                            matricularAlunoEmTurma();
                            System.out.println("Aluno matriculado em turma com sucesso!");
                            tentarNovamente = false;

                        } catch (TurmaInvalidaException | AlunoNaoEncontradoException | IOException e) {
                            System.err.println("Falha ao matricular aluno em turma: " + e.getMessage());
                            System.out.println("Deseja tentar novamente? [s]/[n]");
                            String opcao = sc.nextLine();

                            if (!opcao.equalsIgnoreCase("s")) {
                                tentarNovamente = false;
                            }
                        }
                    }
                    break;

                case 6:
                    while (tentarNovamente) {
                        try {
                            listarAlunosDeUmaTurma();
                            tentarNovamente = false;

                        } catch (TurmaInvalidaException e) {
                            System.out.println("Falha ao listar alunos da turma: " + e.getMessage());
                            System.out.println("Deseja tentar novamente? [s]/[n]");
                            String opcao = sc.nextLine();

                            if (!opcao.equalsIgnoreCase("s")) {
                                tentarNovamente = false;
                            }
                        }
                    }
                    break;

                case 7:
                    while (tentarNovamente) {
                        try {
                            listarAlunosDaFaculdade();
                            tentarNovamente = false;

                        } catch (AlunoNaoEncontradoException e) {
                            System.out.println("Falha ao listar da faculdade: " + e.getMessage());
                            System.out.println("Deseja tentar novamente? [s]/[n]");
                            String opcao = sc.nextLine();

                            if (!opcao.equalsIgnoreCase("s")) {
                                tentarNovamente = false;
                            }
                        }
                    }
                    break;

                case 8:
                    while (tentarNovamente) {
                        try {
                            listarProfessores();
                            tentarNovamente = false;

                        } catch (ProfessorNaoEncontradoException e) {
                            System.out.println("Falha ao listar professores: " + e.getMessage());
                            System.out.println("Deseja tentar novamente? [s]/[n]");
                            String opcao = sc.nextLine();

                            if (!opcao.equalsIgnoreCase("s")) {
                                tentarNovamente = false;
                            }
                        }
                    }
                    break;

                case 9:
                    while (tentarNovamente) {
                        try {
                            listarDisciplinas();
                            tentarNovamente = false;

                        } catch (DisciplinaNaoEncontradaException e) {
                            System.out.println("Falha listar Disciplinas: " + e.getMessage());
                            System.out.println("Deseja tentar novamente? [s]/[n]");
                            String opcao = sc.nextLine();

                            if (!opcao.equalsIgnoreCase("s")) {
                                tentarNovamente = false;
                            }
                        }
                    }
                    break;

                case 10:
                    while (tentarNovamente) {
                        try {
                            listarTurmas();
                            tentarNovamente = false;

                        } catch (TurmaInvalidaException e) {
                            System.out.println("Falha listar a turma: " + e.getMessage());
                            System.out.println("Deseja tentar novamente? [s]/[n]");
                            String opcao = sc.nextLine();

                            if (!opcao.equalsIgnoreCase("s")) {
                                tentarNovamente = false;
                            }

                        }
                    }
                    break;

                case 11:
                    while (tentarNovamente) {
                        try {
                            configurarTurma();
                            tentarNovamente = false;

                        } catch (IntervaloDeUnidadeException | TurmaInvalidaException e) {
                            System.err.println("Falha ao configurar turma: " + e.getMessage());
                            System.out.println("Deseja tentar novamente? [s]/[n]");
                            String opcao = sc.nextLine();

                            if (!opcao.equalsIgnoreCase("s")) {
                                tentarNovamente = false;
                            }
                        }
                    }
                    break;

                case 12:
                    while (tentarNovamente) {
                        try {
                            cadastrarNotas();
                            System.out.println("Notas cadastradas com suceso!");
                            tentarNovamente = false;

                        } catch (AlunoNaoEncontradoException | TurmaInvalidaException e) {
                            System.err.println("Falha ao cadastrar nota: " + e.getMessage());
                            System.out.println("Deseja tentar novamente? [s]/[n]");
                            String opcao = sc.nextLine();

                            if (!opcao.equalsIgnoreCase("s")) {
                                tentarNovamente = false;
                            }
                        }
                    }
                    break;

                case 13:
                    while (tentarNovamente) {
                        try {
                            inativarAluno();
                            System.out.println("Aluno desativado com sucesso!");
                            tentarNovamente = false;

                        } catch (AlunoNaoEncontradoException e) {
                            System.err.println("Falha ao desativar aluno: " + e.getMessage());
                            System.out.println("Deseja tentar novamente? [s]/[n]");
                            String opcao = sc.nextLine();

                            if (!opcao.equalsIgnoreCase("s")) {
                                tentarNovamente = false;
                            }
                        }
                    }
                    break;

                case 14:
                    while(tentarNovamente) {
                        try {
                            encerrarTurmas();
                            System.out.println("Turma encerrada com sucesso!");
                            tentarNovamente = false;

                        } catch (TurmaInvalidaException | ProfessorNaoEncontradoException | IntervaloDeNotaException e) {
                            System.out.println("Falha ao remover encerrar turma: " + e.getMessage());
                            System.out.println("Deseja tentar novamente? [s]/[n]");
                            String opcao = sc.nextLine();

                            if (!opcao.equalsIgnoreCase("s")) {
                                tentarNovamente = false;
                            }
                        }
                    }
                    break;

                case 15:
                    while(tentarNovamente) {
                        try {
                            gerarRelatorioDeTurma();
                            tentarNovamente = false;

                        } catch (TurmaInvalidaException | IOException e) {
                            System.err.println("Falha ao gerar relatório da turma: " + e.getMessage());
                            System.out.println("Deseja tentar novamente? [s]/[n]");
                            String opcao = sc.nextLine();      
                        }
                    }
                    break;

                case 16:
                    while (tentarNovamente) {
                        try {
                            gerarRelatorioDaFaculdade();
                            tentarNovamente = false;

                        } catch (IOException e) {
                            System.err.println("Falha ao gerar relatório da faculdade: " + e.getMessage());
                            System.out.println("Deseja tentar novamente? [s]/[n]");
                            String opcao = sc.nextLine();

                            if (!opcao.equalsIgnoreCase("s")) {
                                tentarNovamente = false;
                            }
                        }
                    }
                    break;

                case 17:
                    while (tentarNovamente) {
                        try {
                            calcularNotaFinalAlunos();
                            tentarNovamente = false;

                        } catch (TurmaInvalidaException | AlunoNaoEncontradoException | IOException e) {
                            System.err.println("Falha ao calcular nora final do eluno: " + e.getMessage());
                            System.out.println("Deseja tentar novamente? [s]/[n]");
                            String opcao = sc.nextLine();

                            if (!opcao.equalsIgnoreCase("s")) {
                                tentarNovamente = false;
                            }
                        }
                    } 
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.\n");
                    break;
            }
        }
    }

    // Cadastra um aluno (cria aluno) -------------------------------------------------------> OK
    // 100% concluido sem erros | NÃO MEXER NESSE METODO |
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

    // Cadastra disciplina (cria disciplina) ------------------------------------------------> OK
    // 100% concluido sem erros | NÃO MEXER NESSE METODO |
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

    public void cadastrarProfessor() throws Exception {
        List<Disciplina> disciplinasDoProfessor = new ArrayList<>();
        // Recebendo dados do professor e cadastrando no sistema
        System.out.println("Informe o nome do professor: ");
        String nome = sc.nextLine();

        System.out.println("Informe o telefone do professor: ");
        String telefone = sc.nextLine();

        System.out.println("Informe o e-mail do professor: ");
        String email = sc.nextLine();

        // Aqui o usuário pode escolher se quer passar todas as disciplinas do professor na criação (conforme o caso de uso)
        System.out.println("Gostaria de adicionar as disciplinas do professor agora? [s]/[n]");
        String escolha = sc.nextLine().toLowerCase();

        boolean comDisciplinas = false;
        String nomeDisciplina = null;
        switch (escolha) {
            case "s":
                System.out.println("Quantidade de disciplinas que deseja adicionar: ");
                int quantidade = sc.nextInt();
                sc.nextLine();

                // Preencher a lista com todas as disciplinas associadas
                for (int i = 0; i < quantidade; i++) {
                    System.out.println("Insira o nome da disciplina: ");
                    nomeDisciplina = sc.nextLine();

                    if (faculdade.existeDisciplina(nomeDisciplina)) {
                        disciplinasDoProfessor.add(faculdade.buscaDisciplina(nomeDisciplina));  // Adiciona o objeto da disciplina na lista temporária de disciplinas do professor

                    } else {
                        throw new DisciplinaInvalidaException("Disciplina inexistente");
                    }
                }

                String matricula = faculdade.adicionarProfessor(nome, telefone, email, disciplinasDoProfessor);
                faculdade.associarProfessorADisciplina(disciplinasDoProfessor, matricula);
                System.out.println("Professor cadastrado.");
                break;

            case "n":
                faculdade.adicionarProfessor(nome, telefone, email);
                System.out.println("Professor cadastrado.");
                break;

            default:
                System.out.println("Opção inválida");
                break;
        }

        faculdade.salvaControleDeTurmas();

    }

    // Cadastra turma (cria turma), se o prefessor não for associado a disciplina ele é associado --> OK
    // 100% concluido sem erros | NÃO MEXER NESSE METODO |
    public void criarTurma() throws ProfessorNaoEncontradoException, DisciplinaNaoEncontradaException, IOException, DisciplinaInvalidaException {
        System.out.println("Informe o nome da disciplina: ");
        String nomeDisciplina = sc.nextLine();

        System.out.println("Informe o id do professor: ");
        String idProfessor = sc.nextLine();

        if (idProfessor == null || idProfessor.trim().isEmpty()) {
            System.out.println("Inválido. O código da disciplina não pode estar vazio.");
            return;
        }

        faculdade.criarTurma(nomeDisciplina, idProfessor);

        Professor professor = faculdade.buscaProfessor(idProfessor);
        Disciplina disciplina = faculdade.buscaDisciplina(nomeDisciplina);

        boolean jaMinistra = false;
        for (Disciplina d : professor.getDisciplinasMinistradas()) {
            if (d.getNomeDisciplina().equalsIgnoreCase(nomeDisciplina)) {
                jaMinistra = true;
                break;
            }
        }

        if (!jaMinistra) {
            professor.getDisciplinasMinistradas().add(disciplina);
        }

        System.out.println("Turma criada com sucesso.");
        faculdade.salvaControleDeTurmas();
    }

    // Cadastra aluno em uma turma -----------------------------------------------------------------> OK
    // 100% concluido sem erros | NÃO MEXER NESSE METODO |
    public void matricularAlunoEmTurma() throws TurmaInvalidaException, AlunoNaoEncontradoException, IOException {
        System.out.println("Informe a matrícula do aluno: ");
        String matricula = sc.nextLine();

        if (matricula == null || matricula.trim().isEmpty()) {
            System.out.println("Inválido. O número da matrícula do aluno não pode estar vazia.");
            return;
        }

        System.out.println("Informe o código da turma: ");
        String codigo = sc.nextLine();

        if (codigo == null || codigo.trim().isEmpty()) {
            System.out.println("Inválido. O código da turma não pode estar vazio.");
            return;
        }

        faculdade.adicionarAlunoATurma(matricula, codigo);
        System.out.println("Aluno matriculado a turma.");

        faculdade.salvaControleDeTurmas();
    }

    // Lista alunos de uma turma específica  -------------------------------------------------------> OK
    // 100% concluido sem erros | NÃO MEXER NESSE METODO |
    public void listarAlunosDeUmaTurma() throws TurmaInvalidaException {
        System.out.println("Insira o código da turma que deseja listar os alunos: ");
        String codigo = sc.nextLine();
        sc.nextLine();

        System.out.println("Alunos da turma: \n" + faculdade.listarAlunosDeTurma(codigo));
    }

    // Lista todos os alunos da faculdade -------------------------------------------> OK
    // 100% concluido sem erros | NÃO MEXER NESSE METODO |
    public void listarAlunosDaFaculdade() throws AlunoNaoEncontradoException {
        System.out.println("Alunos da faculdade: \n" + faculdade.listarAlunosDaFaculdade());
    }

    // Lista os professores ---------------------------------------------------------------------> OK
    // 100% concluido sem erros | NÃO MEXER NESSE METODO |
    public void listarProfessores() throws ProfessorNaoEncontradoException{
        System.out.println("Professores da faculdade: \n" + faculdade.listarProfessores());
    }

    // Lista todas as disciplinas da faculdade  -------------------------------------------------> OK
    // 100% concluido sem erros | NÃO MEXER NESSE METODO |
    public void listarDisciplinas() throws DisciplinaNaoEncontradaException {
        System.out.println("Disciplinas da faculdade: \n" + faculdade.listarDisciplinas());
    }

    // Listar todas as turmas da faculdade ------------------------------------------------------> OK
    // 100% concluido sem erros | NÃO MEXER NESSE METODO |
    public void listarTurmas() throws TurmaInvalidaException {
        System.out.println("Turmas da faculdade: \n" + faculdade.listarTurmas());
    }

    public void configurarTurma() throws IntervaloDeUnidadeException, TurmaInvalidaException {
        System.out.println("Insira o código da turma: ");
        String codigo = sc.nextLine();

        System.out.println("Insira a quantidade de unidades avaliativas: ");
        int qtdUnidesAvaliativas = sc.nextInt();

        final String MENU_CONFIGURACAO = "Escolha o tipo de média: \n" +
                                        "[1] Média simples\n" +
                                        "[2] Média ultima nota vale mais\n" +
                                        "[3] Média descarta a menor nota\n" +
                                        "[0] Sair\n";

        System.out.println(MENU_CONFIGURACAO);
        TiposDeMediaIF tiposDeMediaIF = null;
        int escolha = sc.nextInt();
        sc.nextLine();

        switch (escolha) {
            case 1:
                tiposDeMediaIF = new MediaSimples();
                break;

            case 2:
                tiposDeMediaIF = new MediaUltimaValeMais();
                break;

            case 3:
                tiposDeMediaIF = new MediaDescartaMenorNota();
                break;

            case 0:
                return;

            default:
                System.out.println("Opção inválida");
                break;
        }

        faculdade.configurarTurma(codigo, qtdUnidesAvaliativas, tiposDeMediaIF);
    }

    // Cadastra a nota dos alunos ----------------------------------------------------> OK
    public void cadastrarNotas() throws AlunoNaoEncontradoException, TurmaInvalidaException {
        System.out.println("Informe o código da turma: ");
        String codigo = sc.nextLine();

        if (codigo == null || codigo.trim().isEmpty()) {
            System.out.println("Inválido. O código da turma não pode estar vazio.");
            return;
        }

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

    // Método para inativar aluno da faculdade ---------------------------------------------> OK
    public void inativarAluno() throws AlunoNaoEncontradoException {
        System.out.println("Informe a matrícula do aluno: ");
        String matricula = sc.nextLine();

        if (matricula == null || matricula.trim().isEmpty()) {
            System.out.println("Inválido. A matrícula do aluno não pode estar vazia.");
            return;
        }

        faculdade.desativaAluno(matricula);
    }

//    public void removerAlunoDeTurma() throws AlunoNaoEncontradoException {
//        System.out.println("Informe o código da turma: ");
//        String codigo = sc.nextLine();
//
//        if (codigo == null || codigo.trim().isEmpty()) {
//            System.out.println("Inválido o código da turma não pode estar vazio.");
//            return;
//        }
//
//        System.out.println("Informe a matrícula do aluno: ");
//        String matricula = sc.nextLine();
//
//        if (matricula == null || matricula.trim().isEmpty()) {
//            System.out.println("Inválido. A matrícula do aluno não pode estar vazia.");
//            return;
//        }
//
//        faculdade.removerAluno(matricula);
//    }

    public void calcularNotaFinalAlunos() throws TurmaInvalidaException, AlunoNaoEncontradoException, IOException {
        System.out.println("Insira o código da turma que deseja calcular a nota dos alunos: ");
        String codigo = sc.nextLine();

        System.out.println(faculdade.exibirRelatorioFinalEmTela(codigo));
        faculdade.gerarRelatorioNotaFinalTurma(codigo);
    }

    // Método que gera os relatŕoios da turma -------------------------------------------> OK
    public void gerarRelatorioDeTurma() throws TurmaInvalidaException, IOException {
        System.out.println("Insira o código da turma que deseja gerar o relatório: ");
        String codigo = sc.nextLine();

        if (codigo == null || codigo.trim().isEmpty()) {
            System.out.println("Inválido. O código da turma não pode estar vazio.");
            return;
        }

        faculdade.gerarRelatorioDaTurma(codigo);
        System.out.println("Relatorio gerado com sucesso!\n");
    }

    // Gera o relatório de toda a faculdade ------------------------------------------------------> OK
    // 100% concluido sem erros | NÃO MEXER NESSE METODO |
    public void gerarRelatorioDaFaculdade() throws IOException {
        faculdade.gerarRelatorioDaFaculdade();
        System.out.println("Relatório da turma gerado com sucesso!\n");
    }

    public void encerrarTurmas() throws ProfessorNaoEncontradoException, TurmaInvalidaException, IntervaloDeNotaException {
        System.out.println("Informe o código da turma: ");
        String codigo = sc.nextLine();

        System.out.println("Informe o id do professor: ");
        String idProfessor = sc.nextLine();

        faculdade.encerrarTurma(idProfessor, codigo);
    }
}