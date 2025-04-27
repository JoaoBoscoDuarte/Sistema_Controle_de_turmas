package model.servicos;

import model.disciplina.Disciplina;
import model.exceptions.AlunoNaoEncontradoException;
import model.exceptions.TurmaInvalidaException;
import model.faculdade.Faculdade;
import model.pessoa.Aluno;
import model.pessoa.Professor;
import model.turma.Nota;
import model.turma.Turma;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*  ================| Só altere em caso de urgência! |====================
 *  --------------------Classe 100% concluída-------------------------> OK
 *  Não alterar essa classe pelo amor de deus.
 */

public class GerenciamentoDeArquivos implements Serializable {
    private static final String ARQUIVO_CONTROLE = "controleTurmas.ser";
    private static final String DIRETORIO_ARQUIVOS = "SistemaControleTurmas//src//data";
    private static final String DIRETORIO_RELATORIOS = DIRETORIO_ARQUIVOS + File.separator + "relatorios";
    private static final String NOME_RELATORIO = "relatorio_controle_turmas.txt";
    private GerenciamentoDeTurmas gerenciamentoDeTurmas;
    private GerenciamentoDeAlunos gerenciamentoDeAlunos;

    public GerenciamentoDeArquivos(GerenciamentoDeTurmas gerenciamentoDeTurmas, GerenciamentoDeAlunos gerenciamentoDeAlunos) {
        criarDiretorios();
        this.gerenciamentoDeTurmas = gerenciamentoDeTurmas;
        this.gerenciamentoDeAlunos = gerenciamentoDeAlunos;
    }

    // Permite que os diretórios padrões dos arquivos sempre existam ------------------------------------------>
    private void criarDiretorios() {
        new File(DIRETORIO_ARQUIVOS).mkdirs();
        new File(DIRETORIO_RELATORIOS).mkdirs();
    }

    public void salvarControleTurmas(Faculdade faculdade) throws IOException {
        Path caminhoArquivo = Paths.get(DIRETORIO_ARQUIVOS, ARQUIVO_CONTROLE);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo.toFile()))) {
            oos.writeObject(faculdade);
            System.out.println("Dados salvos com sucesso em: " + caminhoArquivo.toAbsolutePath());

        } catch (Exception e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public Faculdade carregarControleTurmas() throws IOException, ClassNotFoundException {
        Path caminhoArquivo = Paths.get(DIRETORIO_ARQUIVOS, ARQUIVO_CONTROLE);

        if (!caminhoArquivo.toFile().exists()) {
            return new Faculdade(); // Se o arquivo não existir ainda, retorna uma Faculdade nova
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoArquivo.toFile()))) {
            return (Faculdade) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
            throw e;
        }
    }


    // Gera o relatório de toda a faculdade -----------------------------------------------------> Ok
    public void gerarRelatorioFaculdadeTxt(GerenciamentoDeAlunos aluno, GerenciamentoDeProfessores professor, GerenciamentoDeDisciplinas disciplina, GerenciamentoDeTurmas turmas) throws IOException {
        Path caminhoRelatorio = Paths.get(DIRETORIO_RELATORIOS, NOME_RELATORIO);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoRelatorio.toFile()))) {
            writer.write("RELATÓRIO: SISTEMA DE CONTROLE DE TURMAS\n");
            writer.write("Gerado em: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "\n");
            writer.write("|=================================================================================|\n\n");

            writer.write("1. ALUNOS ATIVOS\n");
            if (aluno.getListaAlunos().isEmpty()) {
                writer.write("Sem alunos cadastrados\n");
            } else {
                for (Aluno a : aluno.getListaAlunos()) {
                    writer.write(a.toString() + "\n");
                }
            }
            writer.write("|=================================================================================|\n\n");

            writer.write("2. PROFESSORES ATIVOS\n");
            if (professor.getListaProfessores().isEmpty()) {
                writer.write("Sem professores cadastrados\n");
            } else {
                for (Professor p : professor.getListaProfessores()) {
                    writer.write(p.toString() + "\n");
                }
            }
            writer.write("|=================================================================================|\n\n");

            writer.write("3. DISCIPLINAS\n");
            if (disciplina.getDisciplinas().isEmpty()) {
                writer.write("Sem disciplinas cadastradas\n");
            } else {
                for (Disciplina d : disciplina.getDisciplinas()) {
                    writer.write(d.toString() + "\n");
                }
            }
            writer.write("|=================================================================================|\n\n");

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

    // Gera o relatório de uma turma -----------------------------------------------> OK
    public void gerarRelatorioTurmaTxt(String codigo) throws TurmaInvalidaException, IOException {
        Path caminhoRelatorio = Paths.get(DIRETORIO_RELATORIOS, "relatorio_" + codigo + ".txt");

        Turma turma = gerenciamentoDeTurmas.buscarTurma(codigo);

        if (turma == null) {
            throw new TurmaInvalidaException("Turma não encontrada");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoRelatorio.toFile()))) {
            writer.write("RELATÓRIO DA TURMA: " + codigo + "\n");
            writer.write("Gerado em: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "\n");
            writer.write("|=================================================================================|\n\n");
            writer.write(turma.toString());
        }
    }

    // Gerar Relatorio com nota final de turma
    public void gerarRelatorioNotaFinalTurma(String codigo) throws TurmaInvalidaException, IOException, AlunoNaoEncontradoException {
        Path caminhoRelatorio = Paths.get(DIRETORIO_RELATORIOS, "NotasFinaisAluno" + codigo + ".txt");

        Turma turma = gerenciamentoDeTurmas.buscarTurma(codigo);

        if (turma == null) {
            throw new TurmaInvalidaException("Turma não encontrada");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoRelatorio.toFile()))) {
            writer.write("RELATÓRIO FINAL DA TURMA " + codigo + "\n");
            writer.write("Gerado em: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd//MM//yyyy HH:mm:ss")));
            writer.write("|=================================================================================|\n\n");

            for (Nota n : turma.getNotasAluno()) {
                writer.write("|Nome: " + gerenciamentoDeAlunos.retornaNomeAluno(n.getMatricula()));
                writer.write("|Notas: " + n.getNotas());
                writer.write("|Media: " + gerenciamentoDeTurmas.calculaMedia(n.getNotas(), codigo));
                writer.write("|Situação: " + gerenciamentoDeTurmas.verificarAprovacao(gerenciamentoDeTurmas.calculaMedia(n.getNotas(), codigo)));
            }
        }

        System.out.println("Relatorio criado em: " + caminhoRelatorio);
    }
}
