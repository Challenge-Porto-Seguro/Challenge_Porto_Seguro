package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Login {

    private Cpf cpf;
    private List<Automovel> automoveis = new ArrayList<>();

    public Usuario(String nome, String cpf, String email, String senha) {
        super(nome, email, senha);
        this.cpf = new Cpf(cpf);
    }

    public Usuario(String nome, String senha, String cpf) {
        super(nome, senha);
        this.cpf = new Cpf(cpf);
    }

    public String getCpf() {
        return cpf.toString();
    }

    public void addAutomovel(Automovel automovel){
        this.automoveis.add(automovel);
    }

    public void removeAutomovel(Automovel automovel){
        this.automoveis.remove(automovel);
    }

    public List<Automovel> getAutomoveis() {
        return automoveis;
    }
}
