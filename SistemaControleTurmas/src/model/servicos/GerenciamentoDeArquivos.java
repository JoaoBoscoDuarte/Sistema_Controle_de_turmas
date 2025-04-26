package model.servicos;

import model.disciplina.Disciplina;
import model.faculdade.Faculdade;
import model.pessoa.Aluno;
import model.pessoa.Professor;
import model.turma.Turma;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GerenciamentoDeArquivos implements Serializable {
    private static final String ARQUIVO_CONTROLE = "controleTurmas.ser";
    private static final String DIRETORIO_ARQUIVOS = "SistemaControleTurmas//src//data";
    private static final String DIRETORIO_RELATORIOS = DIRETORIO_ARQUIVOS + File.separator + "relatorios";
    private static final String NOME_RELATORIO = "relatorio_controle_turmas.txt";

    public GerenciamentoDeArquivos() {
        criarDiretorios();
    }

    private void criarDiretorios() {
        new File(DIRETORIO_ARQUIVOS).mkdirs();
        new File(DIRETORIO_RELATORIOS).mkdirs();
    }

    public void salvarControleTurmas(Faculdade faculdade) throws IOException {
        Path caminhoArquivo = Paths.get(DIRETORIO_ARQUIVOS, ARQUIVO_CONTROLE);
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(caminhoArquivo.toFile()))) {
            oos.writeObject(faculdade);
        }
    }

    public Faculdade carregarControleTurmas() throws IOException, ClassNotFoundException {
        Path caminhoArquivo = Paths.get(DIRETORIO_ARQUIVOS, ARQUIVO_CONTROLE);
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(caminhoArquivo.toFile()))) {
            return (Faculdade) ois.readObject();
        }
    }

    // Modifique para receber a Faculdade como parâmetro
    public void gerarRelatorioTxt(GerenciamentoDeAlunos aluno, GerenciamentoDeProfessores professor, GerenciamentoDeDisciplinas disciplina, GerenciamentoDeTurmas turmas) throws IOException {
        Path caminhoRelatorio = Paths.get(DIRETORIO_RELATORIOS, NOME_RELATORIO);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoRelatorio.toFile()))) {
            // Cabeçalho inicial
            writer.write("RELATÓRIO: SISTEMA DE CONTROLE DE TURMAS\n");
            writer.write("Gerado em: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "\n");
            writer.write("|=================================================================================|\n\n");

            // PARTE 1: Alunos
            writer.write("1. ALUNOS ATIVOS\n");
            if (aluno.getListaAlunos().isEmpty()) {
                writer.write("Sem alunos cadastrados\n");
            } else {
                for (Aluno a : aluno.getListaAlunos()) {
                    writer.write(a.toString() + "\n");
                }
            }
            writer.write("|=================================================================================|\n\n");

            // PARTE 2: Professores
            writer.write("2. PROFESSORES ATIVOS\n");
            if (professor.getListaProfessores().isEmpty()) {
                writer.write("Sem professores cadastrados\n");
            } else {
                for (Professor p : professor.getListaProfessores()) {
                    writer.write(p.toString() + "\n");
                }
            }
            writer.write("|=================================================================================|\n\n");

            // PARTE 3: Disciplinas
            writer.write("3. DISCIPLINAS\n");
            if (disciplina.getDisciplinas().isEmpty()) {
                writer.write("Sem disciplinas cadastradas\n");
            } else {
                for (Disciplina d : disciplina.getDisciplinas()) {
                    writer.write(d.toString() + "\n");
                }
            }
            writer.write("|=================================================================================|\n\n");

            // PARTE 4: Turmas
            writer.write("4. TURMAS\n");
            if (turmas.getTurmas().isEmpty()) {
                writer.write("Sem turmas cadastradas\n");
            } else {
                for (Turma t : turmas.getTurmas()) {
                    writer.write(t.toString() + "\n");
                }
            }
            writer.write("|=================================================================================|\n\n");
        }
    }
}