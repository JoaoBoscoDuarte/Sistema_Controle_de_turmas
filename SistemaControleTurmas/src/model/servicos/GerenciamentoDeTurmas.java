package model.servicos;

import model.disciplina.Disciplina;
import model.pessoa.Aluno;
import model.pessoa.Professor;
import model.turma.Turma;
import model.turma.media.TiposDeMediaIF;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GerenciamentoDeTurmas {
    private List<Turma> turmas;
    private GerenciamentoDeAlunos gerenciamentoDeAlunos;
    private GerenciamentoDeProfessores gerenciamentoDeProfessores;
    private Turma turma;
    private Aluno aluno;

    public GerenciamentoDeTurmas(GerenciamentoDeAlunos gerenciamentoDeAlunos) {
        this.turmas = new ArrayList<>();
        this.gerenciamentoDeAlunos = new GerenciamentoDeAlunos();
        this.gerenciamentoDeProfessores = new GerenciamentoDeProfessores();
    }

    public void addAluno(String matricula, Turma turma) {
        for (Turma t : turmas) {
            if (t.equals(turma) && !(t.getMatriculasAlunos().contains(matricula))) {
                t.adicionarAluno(matricula);
            }
        }
    }

    public void criarTurma(Disciplina disciplina, Professor professor, int qtdUnidadesAvaliativas, TiposDeMediaIF tipoDeMedia) {
        Turma turma = new Turma(disciplina, professor, qtdUnidadesAvaliativas, tipoDeMedia);
        turmas.add(turma);
    }

    public void adicionarAlunoATurma(String matricula, Turma turma) {
        if (gerenciamentoDeAlunos.existeAluno(matricula)) {
            turma.adicionarAluno(matricula);
        }
    }

    public void adicionarProfessorATurma(String matricula, Turma turma) throws Exception {
        if (gerenciamentoDeProfessores.existeProfessor(matricula)) {
            turma.adicionarProfessor(matricula);
        }
    }

    public void atribuirQuantidadeDeUnidadesAvaliativas(int qtdUnidadesAvaliativas, Turma turma) {
        for (Turma t : turmas) {
            if (t.equals(turma)) {
                t.setQtdUnidadesAvaliativas(qtdUnidadesAvaliativas);
            }
        }
    }

    public void cadastrarNotasUnidade(Turma turma, int unidade, Map<String, Double> notasAlunos) {
        // Cadastra cada nota
        for (Map.Entry<String, Double> entry : notasAlunos.entrySet()) {
            String matricula = entry.getKey();
            Double nota = entry.getValue();

            turma.cadastrarNota(matricula, unidade, nota);
        }
    }

    public void removerAluno(String matricula) {

         if(aluno.isAtivo() && turmas.contains(aluno.getMatricula())){
             turmas.remove(aluno.getMatricula());
    }

    //    public void removerAlunoDeTurma(String matricula, Turma turma) {
//        // Verifica se a turma existe na lista
//        if (!turmas.contains(turma)) {
//            throw new IllegalArgumentException("Turma não encontrada");
//        }
//
//        // Remove o aluno da turma
//        turma.removerAluno(matricula);
//
//        // Opcional: remover também as notas do aluno
//        turma.getNotasAluno().removeIf(nota -> nota.getAluno().getMatricula().equals(matricula));
    }

        public String listarTodasTurmas() {
            if () {
                return "Nenhuma turma cadastrada";
            }

            //StringBuilder lista = new StringBuilder();
            //lista.append("=== TURMAS CADASTRADAS ===\n");

            //for (Turma turma : turmas) {
            //  lista.append("Código: ").append(turma.getCodigoTurma())
            //          .append(" | Disciplina: ").append(turma.getDisciplina().getNomeDisciplina())
            //          .append(" | Professor: ").append(turma.getProfessor().getNome())
            //          .append(" | Alunos: ").append(turma.getMatriculasAlunos().size())
            //          .append("\n");
            //}

            //return lista.toString();
        }
}
