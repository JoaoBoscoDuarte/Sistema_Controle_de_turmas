package model.turma;

import model.disciplina.Disciplina;
import model.pessoa.Aluno;
import model.pessoa.Pessoa;
import model.pessoa.Professor;
import model.turma.media.TiposDeMediaIF;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Turma {

    private Disciplina disciplina;
    private Professor professor;
    private int qtdUnidadesAvaliativas;

    private List<Nota> notasAluno;
    private List<String> matriculasAlunos;
    private List<String> matriculasProfessores;

    private TiposDeMediaIF tipoDeMedia;
    private LocalDate dataCriacaoTurma;
    private String codigoTurma;
    private static int contador = 0;

    public Turma(Disciplina disciplina, Professor professor, int qtdUnidadesAvaliativas, TiposDeMediaIF tipoDeMedia) {
        this.disciplina = disciplina;
        this.professor = professor;
        this.qtdUnidadesAvaliativas = qtdUnidadesAvaliativas;
        this.tipoDeMedia = tipoDeMedia;
        this.notasAluno = new ArrayList<>();
        this.matriculasAlunos = new ArrayList<>();
        this.matriculasProfessores = new ArrayList<>();
        this.dataCriacaoTurma = LocalDate.now();
        this.codigoTurma = geraCodigoTurma();
    }

    public void cadastrarNota(String matricula, int unidade, double nota) {
        if (unidade < 2 || unidade > qtdUnidadesAvaliativas) {
            throw new IllegalArgumentException("Unidade inválida");
        }

        for (Nota n : notasAluno) {
            if (n.getAluno().getMatricula().equals(matricula)) {
                n.adicionarNota(unidade, nota);
                return;
            }
        }

        throw new IllegalArgumentException("Aluno não encontrado na turma");
    }

    public void adicionarAluno(String matricula) {
        if (!matriculasAlunos.contains(matricula)) {
            matriculasAlunos.add(matricula);
        }
    }

    public void adicionarProfessor(String matricula) {
        if (!matriculasProfessores.contains(matricula)) {
            matriculasProfessores.add(matricula);
        }
    }

    public double calcularMedia(Aluno aluno) {
        for (Nota nota : notasAluno) {
            if (nota.getAluno().equals(aluno)) {
                // Obtém todas as notas do aluno (de todas as unidades)
                Map<Integer, Double> notasPorUnidade = nota.getNotasPorUnidade();

                // Converte os valores do mapa para uma lista de doubles
                List<Double> todasNotas = new ArrayList<>(notasPorUnidade.values());

                // Delega o cálculo para a estratégia de média
                return tipoDeMedia.calcularMedia(todasNotas);
            }
        }
        throw new IllegalArgumentException("Aluno não encontrado na turma");
    }

    public String verificarAprovacao(double media) {
        String exibir = "";

        if (media < 7) {
            exibir = "REPROVADO";

        } else if (media >= 7) {
            exibir = "APROVADO";
        }

        return exibir;
    }

    public List<String> getMatriculasAlunos() {
        return new ArrayList<>(matriculasAlunos);
    }

    public int getQtdUnidadesAvaliativas() {
        return qtdUnidadesAvaliativas;
    }

    public void setQtdUnidadesAvaliativas(int qtdUnidadesAvaliativas) {
        this.qtdUnidadesAvaliativas = qtdUnidadesAvaliativas;
    }

    public String formataData() {
        return dataCriacaoTurma.format(DateTimeFormatter.ofPattern("ddMMyyyy"));
    }

    public static String geraCodigoTurma() {
        contador++;
        String dataFormatada = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        return String.format("%s-%05d", dataFormatada, contador);
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public Professor getProfessor() {
        return professor;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return qtdUnidadesAvaliativas == turma.qtdUnidadesAvaliativas && Objects.equals(disciplina, turma.disciplina) && Objects.equals(professor, turma.professor) && Objects.equals(dataCriacaoTurma, turma.dataCriacaoTurma) && Objects.equals(codigoTurma, turma.codigoTurma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(disciplina, professor, qtdUnidadesAvaliativas, dataCriacaoTurma, codigoTurma);
    }

    @Override
    public String toString() {
        return "Turma{" +
                "nomeDisciplina=" + disciplina +
                ", nomeProfessor=" + professor +
                ", qtdUnidadesAvaliativas=" + qtdUnidadesAvaliativas +
                ", dataCriacaoTurma=" + dataCriacaoTurma +
                ", codigoTurma='" + codigoTurma + '\'' +
                '}';
    }
}