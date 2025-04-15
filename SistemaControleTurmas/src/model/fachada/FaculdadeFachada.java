package model.fachada;

import model.faculdade.Faculdade;
import model.servicos.GerenciamentoDeDisciplinas;
import model.servicos.GerenciamentoDeAlunos;
import model.servicos.GerenciamentoDeProfessores;
import model.servicos.GerenciamentoDeTurmas;

public class FaculdadeFachada {
    private GerenciamentoDeDisciplinas gerenciadorDeDisciplinas;
    private GerenciamentoDeProfessores gerenciamentoDeProfessores;
    private GerenciamentoDeAlunos gerenciamentoDeAlunos;
    private GerenciamentoDeTurmas gerenciamentoDeTurmas;
    private Faculdade faculdade;

    public FaculdadeFachada() {
        this.gerenciadorDeDisciplinas = gerenciadorDeDisciplinas;
        this.gerenciamentoDeProfessores = gerenciamentoDeProfessores;
        this.gerenciamentoDeAlunos = gerenciamentoDeAlunos;
        this.gerenciamentoDeTurmas = gerenciamentoDeTurmas;
        this.faculdade = faculdade;
    }


}
