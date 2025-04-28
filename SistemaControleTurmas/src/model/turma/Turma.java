package model.turma;

import model.disciplina.Disciplina;
import model.exceptions.AlunoNaoEncontradoException;
import model.pessoa.Aluno;
import model.pessoa.Professor;
import model.servicos.GerenciamentoDeAlunos;
import model.turma.media.MediaDescartaMenorNota;
import model.turma.media.MediaSimples;
import model.turma.media.TiposDeMediaIF;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*  ================| Só altere em caso de urgência! |====================
 *  ----------------------Classe 100% concluída-----------------------> OK
 */

public class Turma implements Serializable {
    private Disciplina disciplina;
    private Professor professor;
    private int numeroUnidades;
    private TiposDeMediaIF tipoDeMedia;
    private boolean unidadesAvalaitivasMudada = false;
    private GerenciamentoDeAlunos gerenciamentoDeAlunos;
    private boolean ativo = true;

    private List<Nota> notasAluno;
    private List<String> matriculasProfessores;

    private String codigoTurma;
    private static transient int contador = 1;

    public Turma(Disciplina disciplina, Professor professor, GerenciamentoDeAlunos gerenciamentoDeAlunos) {
        this.disciplina = disciplina;
        this.professor = professor;
        this.notasAluno = new ArrayList<>();
        this.matriculasProfessores = new ArrayList<>();
        this.codigoTurma = geraCodigoTurma();
        this.tipoDeMedia = new MediaSimples();           // valor padrão
        this.numeroUnidades = 0;                         // Valor padrão
        this.gerenciamentoDeAlunos = gerenciamentoDeAlunos;
    }

    public static String geraCodigoTurma() {
        return LocalDate.now().getYear() + String.format("%04d" , contador++);
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public int getNumeroUnidades() {
        return numeroUnidades;
    }

    public void setNumeroUnidades(int numeroUnidades) {
        this.numeroUnidades = numeroUnidades;
    }

    public TiposDeMediaIF getTipoDeMedia() {
        return tipoDeMedia;
    }

    public void setTipoDeMedia(TiposDeMediaIF tipoDeMedia) {
        this.tipoDeMedia = tipoDeMedia;
    }

    public List<Nota> getNotasAluno() {
        return notasAluno;
    }

    public void setNotasAluno(List<Nota> notasAluno) {
        this.notasAluno = notasAluno;
    }

    public List<String> getMatriculasProfessores() {
        return matriculasProfessores;
    }

    public void setMatriculasProfessores(List<String> matriculasProfessores) {
        this.matriculasProfessores = matriculasProfessores;
    }

    public boolean isUnidadesAvalaitivasMudada() {
        return unidadesAvalaitivasMudada;
    }

    public void setUnidadesAvalaitivasMudada(boolean unidadesAvalaitivasMudada) {
        this.unidadesAvalaitivasMudada = unidadesAvalaitivasMudada;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public void setCodigoTurma(String codigoTurma) {
        this.codigoTurma = codigoTurma;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Turma.contador = contador;
    }

    public String exibirTipoDeMedia() {
        final String MEDIA_SIMPLES = "Media simples";
        final String ULTIMA_VALE_MAIS = "Media ultima nota vale mais";
        final String MEDIA_DESCARTA_MENOR = "Media descarta a menor";

        if (getTipoDeMedia().getClass().equals(MediaDescartaMenorNota.class)) {
            return MEDIA_DESCARTA_MENOR;

        } else if (getTipoDeMedia().getClass().equals(MediaSimples.class)) {
            return MEDIA_SIMPLES;

        }

        return ULTIMA_VALE_MAIS;
    }

    public String exibirAlunosAssociados() throws AlunoNaoEncontradoException {
        String exibir = "";
        for (Nota n : getNotasAluno()) {
            exibir += gerenciamentoDeAlunos.buscaAluno(n.getMatricula()).getNome() + " (" + n.getMatricula() + "), ";
        }

        return exibir;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return numeroUnidades == turma.numeroUnidades && unidadesAvalaitivasMudada == turma.unidadesAvalaitivasMudada && Objects.equals(disciplina, turma.disciplina) && Objects.equals(professor, turma.professor) && Objects.equals(tipoDeMedia, turma.tipoDeMedia) && Objects.equals(gerenciamentoDeAlunos, turma.gerenciamentoDeAlunos) && Objects.equals(notasAluno, turma.notasAluno) && Objects.equals(matriculasProfessores, turma.matriculasProfessores) && Objects.equals(codigoTurma, turma.codigoTurma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(disciplina, professor, numeroUnidades, tipoDeMedia, unidadesAvalaitivasMudada, gerenciamentoDeAlunos, notasAluno, matriculasProfessores, codigoTurma);
    }

    @Override
    public String toString() {
        try {
            return "| Disciplina associada: " + disciplina.getNomeDisciplina() + " | \n" +
                    "| Professor: " + professor.getNome() + " | \n" +
                    "| Nº Unidades Avaliativas: " + getNumeroUnidades() + " | \n" +
                    "| Tipo de Média: " + exibirTipoDeMedia() + " | \n" +
                    "| Alunos associados: " + exibirAlunosAssociados() + " | \n" +
                    "| Codigo da turma: " + getCodigoTurma() + " | \n";
        } catch (AlunoNaoEncontradoException e) {
            throw new RuntimeException(e);
        }
    }

}