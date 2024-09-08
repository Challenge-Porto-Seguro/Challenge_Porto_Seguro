package com.example.model.usuarios;

import com.example.model.Automovel;
import com.example.model.Cnpj;
import com.example.model.Orcamento;

public class Oficina extends Login {

    private String inscricaoEstadual;
    private Cnpj cnpj;
    private double valorAPagar;

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
    @Override
    public void addOrcamento(Automovel automovel, Orcamento orcamento) {
        automovel.getDiagnostico().addOrcamento(orcamento);
        valorAPagar += 5;
    }

    @Override
    public String toString() {
        return "Oficina: " + "Nome: " + getNome() + ", Email: " + getEmail() + cnpj;
    }
}
