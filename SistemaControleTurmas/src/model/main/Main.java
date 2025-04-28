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
        faculdade.carregaContador();
        faculdade.carregaContadorTurma();

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
                            "[11] Configurar Turma\n" +                  //OK
                            "[12] Cadastrar notas\n" +                   //OK
                            "[13] Inativar um aluno\n" +
                            "[14] Encerrar Turmas\n" +
                            "[15] Gerar relatório de turma\n" +          //OK
                            "[16] Gerar relatório da faculdade\n" +      //OK
                            "[17] Calcular nota final dos alunos\n" +
                            "[18] Remover aluno de uma turma\n" +        //OK
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
                            tentarNovamente = false;

                        } catch (PessoaInvalidaException e) {
                            System.err.println("Falha ao cadastrar aluno: " + e.getMessage());

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

                        } catch (ProfessorNaoEncontradoException |DisciplinaNaoEncontradaException | DisciplinaInvalidaException | IOException e) {
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
                            System.err.println("Falha ao listar alunos da turma: " + e.getMessage());

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
                            System.err.println("Falha ao listar da faculdade: " + e.getMessage());

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
                            System.err.println("Falha ao listar professores: " + e.getMessage());

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
                            System.err.println("Falha listar Disciplinas: " + e.getMessage());

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
                            System.err.println("Falha listar a turma: " + e.getMessage());

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
                            tentarNovamente = false;

                        } catch (AlunoNaoEncontradoException | TurmaInvalidaException | IntervaloDeNotaException e) {
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
                    while (tentarNovamente) {
                        try {
                            encerrarTurmas();
                            tentarNovamente = false;

                        } catch (TurmaInvalidaException | ProfessorNaoEncontradoException | IntervaloDeNotaException e) {
                            System.err.println("Falha ao encerrar turma: " + e.getMessage());

                            System.out.println("Deseja tentar novamente? [s]/[n]");
                            String opcao = sc.nextLine();

                            if (!opcao.equalsIgnoreCase("s")) {
                                tentarNovamente = false;
                            }
                        }
                    }
                    break;

                case 15:
                    while (tentarNovamente) {
                        try {
                            gerarRelatorioDeTurma();
                            tentarNovamente = false;

                        } catch (TurmaInvalidaException | IOException e) {
                            System.err.println("Falha ao gerar relatório da turma: " + e.getMessage());

                            System.out.println("Deseja tentar novamente? [s]/[n]");
                            String opcao = sc.nextLine();

                            if (!opcao.equalsIgnoreCase("s")) {
                                tentarNovamente = false;
                            }
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

                case 18:
                    while (tentarNovamente) {
                        try {
                            removerAlunoDeTurma();
                            tentarNovamente = false;

                        } catch (AlunoNaoEncontradoException e) {
                            System.out.println("Falha ao remover aluno da turma: " + e.getMessage());

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

    // Cadastra um aluno (cria aluno) -------------------------------------------------000----------> OK
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
        System.out.println("Aluno cadastrado.");
        faculdade.salvaControleDeTurmas();
        faculdade.salvaContador();
    }

    // Cadastra disciplina (cria disciplina) -------------------------------------------------------> OK
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
        System.out.println("Disciplina cadastrada");
        faculdade.salvaControleDeTurmas();
        faculdade.salvaContador();
    }

    // Cadastra Professor (cria professor) ---------------------------------------------------------> OK
    // 100% concluido sem erros | NÃO MEXER NESSE METODO |
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

        String nomeDisciplina;
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
        faculdade.salvaContador();
    }

    // Cadastra turma (cria turma), se o prefessor não for associado a disciplina ele é associado --> OK
    // 100% concluido sem erros | NÃO MEXER NESSE METODO |
    public void criarTurma() throws ProfessorNaoEncontradoException, DisciplinaNaoEncontradaException, IOException, DisciplinaInvalidaException {
        System.out.println("Informe o nome da disciplina: ");
        String nomeDisciplina = sc.nextLine();

        System.out.println("Informe o id do professor: ");
        String idProfessor = sc.nextLine();

        if (idProfessor == null || idProfessor.trim().isEmpty()) {
            throw new ProfessorNaoEncontradoException("O id do professor não pode estar vazio.");
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
        faculdade.salvaContador();
        faculdade.salvaContadorTurma();
    }

    // Cadastra aluno em uma turma -----------------------------------------------------------------> OK
    // 100% concluido sem erros | NÃO MEXER NESSE METODO |
    public void matricularAlunoEmTurma() throws TurmaInvalidaException, AlunoNaoEncontradoException, IOException {
        System.out.println("Informe a matrícula do aluno: ");
        String matricula = sc.nextLine();

        if (matricula == null || matricula.trim().isEmpty()) {
            throw new AlunoNaoEncontradoException("O número da matrícula do aluno não pode estar vazio.");
        }

        System.out.println("Informe o código da turma: ");
        String codigo = sc.nextLine();

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new TurmaInvalidaException("O código da turma não pode estar vazio.");
        }

        faculdade.adicionarAlunoATurma(matricula, codigo);
        System.out.println("Aluno matriculado a turma.");

        faculdade.salvaControleDeTurmas();
        faculdade.salvaContador();
    }

    // Lista alunos de uma turma específica  -------------------------------------------------------> OK
    // 100% concluido sem erros | NÃO MEXER NESSE METODO |
    public void listarAlunosDeUmaTurma() throws TurmaInvalidaException {
        System.out.println("Insira o código da turma que deseja listar os alunos: ");
        String codigo = sc.nextLine();

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new TurmaInvalidaException("O código da turma não pode ser vazio.");
        }

        System.out.println("Alunos da turma: \n" + faculdade.listarAlunosDeTurma(codigo));
    }

    // Lista todos os alunos da faculdade ----------------------------------------------------------> OK
    // 100% concluido sem erros | NÃO MEXER NESSE METODO |
    public void listarAlunosDaFaculdade() throws AlunoNaoEncontradoException {
        System.out.println("Alunos da faculdade: \n" + faculdade.listarAlunosDaFaculdade());
    }

    // Lista os professores ------------------------------------------------------------------------> OK
    // 100% concluido sem erros | NÃO MEXER NESSE METODO |
    public void listarProfessores() throws ProfessorNaoEncontradoException{
        System.out.println("Professores da faculdade: \n" + faculdade.listarProfessores());
    }

    // Lista todas as disciplinas da faculdade  ---------------------------------------------------> OK
    // 100% concluido sem erros | NÃO MEXER NESSE METODO |
    public void listarDisciplinas() throws DisciplinaNaoEncontradaException {
        System.out.println("Disciplinas da faculdade: \n" + faculdade.listarDisciplinas());
    }

    // Listar todas as turmas da faculdade --------------------------------------------------------> OK
    // 100% concluido sem erros | NÃO MEXER NESSE METODO |
    public void listarTurmas() throws TurmaInvalidaException {
        System.out.println("Turmas da faculdade: \n" + faculdade.listarTurmas());
    }

    // configura a turma (adiciona quantidade de unidades e muda o tipo de média) -----------------> OK
    // 100% concluido sem erros | NÃO MEXER NESSE METODO |
    public void configurarTurma() throws IntervaloDeUnidadeException, TurmaInvalidaException, IOException {
        System.out.println("Insira o código da turma: ");
        String codigo = sc.nextLine();

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new TurmaInvalidaException("O código da turma não pode estar vazio.");
        }

        System.out.println("Insira a quantidade de unidades avaliativas: ");
        int qtdUnidesAvaliativas = sc.nextInt();

        final String MENU_CONFIGURACAO = """
                Escolha o tipo de média:\s
                [1] Média simples
                [2] Média ultima nota vale mais
                [3] Média descarta a menor nota
                [0] Sair
                """;

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
        faculdade.salvaControleDeTurmas();
    }

    // Cadastra a nota dos alunos de uma turma ----------------------------------------------------> OK
    // 100% concluido sem erros | NÃO MEXER NESSE METODO |
    public void cadastrarNotas() throws AlunoNaoEncontradoException, TurmaInvalidaException, IntervaloDeUnidadeException, IntervaloDeNotaException, IOException {
        // Recebe código da turma e verifica se válida
        System.out.println("Informe o código da turma: ");
        String codigo = sc.nextLine();
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new TurmaInvalidaException ("O código da turma não pode estar vazio.");
        }

        Turma turma = faculdade.buscarTurma(codigo); // Usando a fachada para buscar a turma

        if (turma.getNotasAluno().isEmpty()) { // Verifica se a turma tem alunos
            throw new AlunoNaoEncontradoException("Não há alunos matriculados nesta turma.");
        }

        // recebe quantidade de unidades avaliativas e verifica se válida
        System.out.println("Informe a unidade avaliativa: ");
        int unidade = sc.nextInt();
        sc.nextLine();
        if (turma.getNumeroUnidades() == 0) {
            throw new IntervaloDeUnidadeException("Número de unidades avaliativas não foi definido");

        } else if (unidade > turma.getNumeroUnidades()) {
            throw new IntervaloDeUnidadeException("Unidade avaliativa deve ser entre 1 e " + turma.getNumeroUnidades());
        }

        // Para cada aluno da turma -> solicita a nota da unidade especificada
        for (Nota nota : turma.getNotasAluno()) {
            System.out.println("Informe a nota do aluno " + faculdade.buscarAluno(nota.getMatricula()).getNome() + "(" + nota.getMatricula() + ")" + " para a unidade " + unidade + ": ");
            String notaTexto = sc.nextLine();
            notaTexto = notaTexto.replace(",", ".");
            double notaAluno = Double.parseDouble(notaTexto);
            faculdade.cadastrarNotasUnidade(codigo, unidade, nota.getMatricula(), notaAluno);
        }

        faculdade.salvaControleDeTurmas();
    }

    // Método para inativar aluno da faculdade ----------------------------------------------------> Precisa confirmação
    public void inativarAluno() throws AlunoNaoEncontradoException, IOException {
        System.out.println("Informe a matrícula do aluno: ");
        String matricula = sc.nextLine();

        if (matricula == null || matricula.trim().isEmpty()) {
            throw new AlunoNaoEncontradoException("O número da matrícula do aluno não pode estar vazio.");
        }

        faculdade.desativaAluno(matricula);
        faculdade.salvaControleDeTurmas();
    }

    // Cadastra a nota dos alunos de uma turma ----------------------------------------------------> OK
    // 100% concluido sem erros | NÃO MEXER NESSE METODO |
    public void removerAlunoDeTurma() throws AlunoNaoEncontradoException, IOException {
        System.out.println("Informe o código da turma: ");
        String codigo = sc.nextLine();

        if (codigo == null || codigo.trim().isEmpty()) {
            System.out.println("Inválido o código da turma não pode estar vazio.");
            return;
        }
        System.out.println("Informe a matrícula do aluno: ");
        String matricula = sc.nextLine();

        if (matricula == null || matricula.trim().isEmpty()) {
            System.out.println("Inválido. A matrícula do aluno não pode estar vazia.");
            return;
        }

        faculdade.removerAluno(matricula);
        faculdade.salvaControleDeTurmas();
    }

    public void calcularNotaFinalAlunos() throws TurmaInvalidaException, AlunoNaoEncontradoException, IOException {
        System.out.println("Insira o código da turma que deseja calcular a nota dos alunos: ");
        String codigo = sc.nextLine();

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new TurmaInvalidaException("O código da turma não pode estar vazio.");
        }

        System.out.println(faculdade.exibirRelatorioFinalEmTela(codigo));
        faculdade.gerarRelatorioNotaFinalTurma(codigo);
        faculdade.salvaControleDeTurmas();
    }

    // gerar relatorio de uma turma específica (cria aluno) ----------------------------------------> OK
    // 100% concluido sem erros | NÃO MEXER NESSE METODO |
    public void gerarRelatorioDeTurma() throws TurmaInvalidaException, IOException {
        System.out.println("Insira o código da turma que deseja gerar o relatório: ");
        String codigo = sc.nextLine();

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new TurmaInvalidaException("O código da turma não pode estar vazio.");
        }

        faculdade.gerarRelatorioDaTurma(codigo);
        System.out.println("Relatorio fa turma" + codigo + "gerado com sucesso!\n");
    }

    // Gera o relatório de toda a faculdade ------------------------------------------------------> OK
    // 100% concluido sem erros | NÃO MEXER NESSE METODO |
    public void gerarRelatorioDaFaculdade() throws IOException {
        faculdade.gerarRelatorioDaFaculdade();
        System.out.println("Relatório da faculdade gerado com sucesso!\n");
    }

    public void encerrarTurmas() throws ProfessorNaoEncontradoException, TurmaInvalidaException, IntervaloDeNotaException, IOException {
        System.out.println("Informe o código da turma: ");
        String codigo = sc.nextLine();

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new TurmaInvalidaException("O código da turma não pode estar vazio.");
        }

        System.out.println("Informe o id do professor: ");
        String idProfessor = sc.nextLine();

        if (idProfessor == null || idProfessor.trim().isEmpty()) {
            throw new ProfessorNaoEncontradoException("O id do professor não pode estar vazio.");
        }

        faculdade.encerrarTurma(idProfessor, codigo);
        faculdade.salvaControleDeTurmas();
    }
}