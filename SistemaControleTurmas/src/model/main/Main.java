package model.main;

import model.faculdade.Faculdade;

import java.util.Scanner;

public class Main {
    public Faculdade faculdade;
    private Scanner sc;

    public Main() {
        this.faculdade = new Faculdade();
        this.sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.executar();
    }

    public void executar() {

        boolean repetir = true;
        while (repetir) {
            final String menuPrincipal =
                    "===========| Sistema de controle de turmas |===========\n" +
                            "[1] Cadastrar Aluno\n" +
                            "[2] Cadastrar disciplina\n" +
                            "[3] Cadastrar professor\n" +
                            "[4] Criar Turma\n" +
                            "[5] Matricular Aluno em uma Turma\n" +
                            "[6] Listar Alunos de uma Turma\n" +
                            "[7] Configurar turma\n" +
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
                    cadastrarAluno();
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
        }
    }
}
