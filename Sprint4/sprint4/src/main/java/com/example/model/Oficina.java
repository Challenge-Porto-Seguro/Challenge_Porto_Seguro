package com.example.model;

public class Oficina extends Login {

    private String inscricaoEstadual;
    private Cnpj cnpj;

    public Oficina(String nome, String cnpj, String email, String senha, String inscricaoEstadual) {
        super(nome, email, senha);
        this.cnpj = new Cnpj(cnpj);
        this.inscricaoEstadual = inscricaoEstadual;

    }

    public Oficina(String nome, String senha, String inscricaoEstadual, String cnpj) {
        super(nome, senha);
        this.inscricaoEstadual = inscricaoEstadual;
        this.cnpj = new Cnpj(cnpj);
    }


    public String getCnpj() {
        return cnpj.toString();
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    @Override
    public String toString() {
        return "Oficina: " + "Nome: " + getNome() + ", Email: " + getEmail() + cnpj;
    }
}
