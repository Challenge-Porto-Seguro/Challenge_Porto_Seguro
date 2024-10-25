package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Oficina extends Login {

    private String inscricaoEstadual;
    private Cnpj cnpj;
    private double valorAPagar;
    List<Diagnostico> diagnosticos = new ArrayList<>();

    public Oficina(String nome, String cnpj, String email, String senha, String inscricaoEstadual) {
        super(nome, email, senha);
        this.cnpj = new Cnpj(cnpj);
        this.inscricaoEstadual = inscricaoEstadual;

    }

    public double getValorAPagar() {
        return valorAPagar;
    }

    public Cnpj getCnpj() {
        return cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    //Um oficina pode fazer quantos orçamento quiser porem paga um valor a cada orçamento
    public void addOrcamento(Diagnostico diagnostico, Orcamento orcamento) {
        diagnostico.criarOrcamento(orcamento);
        diagnosticos.add(diagnostico);
        valorAPagar += 5;
    }

    @Override
    public String toString() {
        return "Oficina: " + "Nome: " + getNome() + ", Email: " + getEmail() + cnpj;
    }
}
