package com.example.domain.usuarios;

import com.example.domain.Automovel;
import com.example.domain.Cnpj;
import com.example.domain.Endereco;
import com.example.domain.Orcamento;

public class Oficina extends MinimoInformacao {

    private String inscricaoEstadual;
    private Cnpj cnpj;
    private double valorAPagar;

    public Oficina(String nome, String cnpj, String email, String senha, Endereco endereco, String inscricaoEstadual) {
        super(nome, email, senha, endereco);
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
    public void addOrcamento(int idAutomovel, Orcamento orcamento) {
        Automovel automovel = findById(idAutomovel).orElseThrow(() -> new RuntimeException("Id invalido"));
        automovel.getDiagnostico().addOrcamento(orcamento);
        valorAPagar += 5;
    }

    @Override
    public String toString() {
        return "Oficina: " + "Nome: " + getNome() + ", Email: " + getEmail() + cnpj;
    }
}
