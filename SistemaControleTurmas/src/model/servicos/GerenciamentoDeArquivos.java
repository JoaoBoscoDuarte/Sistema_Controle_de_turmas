package model.servicos;

import model.disciplina.Disciplina;
import model.faculdade.Faculdade;
import model.pessoa.Aluno;
import model.pessoa.Professor;
import model.turma.Turma;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GerenciamentoDeArquivos {
    private GerenciamentoDeAlunos aluno;
    private GerenciamentoDeProfessores professor;
    private GerenciamentoDeDisciplinas disciplina;
    private GerenciamentoDeTurmas turma;

    private static final String CONTROLE_TURMAS = "controleTurmas.ser";

    public void salvarControleTurmas(Faculdade faculdade) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(CONTROLE_TURMAS))) {
            oos.writeObject(faculdade);
        }
    }

    public Faculdade carregarControleTurmas() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(CONTROLE_TURMAS))) {
            return (Faculdade) ois.readObject();
        }
    }

    public Faculdade carregarOuCriarEstoque() {
        try {
            return carregarControleTurmas();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Criando novo ser");
            return new Faculdade();
        }
    }

    public void gerarRelatorioTxt() throws IOException {
        final String NOME_ARQUIVO = "RelatorioSistemaControleTurmas";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO))){
            // Cabeçalho inicial (Apresentação com data)
            writer.write("RELATÓRIO: SISTEMA DE CONTROLE DE TURMAS\n");
            writer.write("Gerado em: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "\n");
            writer.write("|=================================================================================|\n\n");

            // PARTE 1: Alunos da faculdade (Todos os alunos ativos e suas informações da faculdade)
            writer.write("1. ALUNOS ATIVOS\n");
            for (Aluno a : aluno.getListaAlunos()) {
                writer.write(a.toString());
            }
            writer.write("|=================================================================================|\n\n");

            // PARTE 2: Professores da faculdade
            writer.write("2. PROFESSORES ATIVOS\n");
            for (Professor p : professor.getListaProfessores()) {
                writer.write(p.toString());
            }
            writer.write("|=================================================================================|\n\n");

            // PARTE 3: Disciplinas da faculdade
            writer.write("3. DISCIPLINAS\n");
            for (Disciplina d : disciplina.getDisciplinas()) {
                writer.write(d.toString());
            }
            writer.write("|=================================================================================|\n\n");

            // PARTE 4: Turmas da faculdade
            writer.write("4. TURMAS\n");
            for (Turma t : turma.getTurmas()) {
                writer.write(t.toString());
            }
            writer.write("|=================================================================================|\n\n");
        }
    }
}
