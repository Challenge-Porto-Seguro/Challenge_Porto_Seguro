package com.example.domain.usuarios;

import com.example.FazerOrcamento;
import com.example.domain.Orcamento;
import com.example.enums.StatusOrcamento;
import com.example.domain.Automovel;
import com.example.domain.Endereco;

import java.util.ArrayList;
import java.util.List;

public abstract class MinimoInformacao implements FazerOrcamento, VerificaDados {

    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private Endereco endereco;
    private List<Automovel> automoveis = new ArrayList<>();
    private List<Orcamento> orcamentos = new ArrayList<>();

    public MinimoInformacao(String nome, String cpf, String email, String senha, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getCpf() {
        return cpf;
    }

    public List<Automovel> getAutomoveis() {
        return automoveis;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addAutomovel(Automovel automovel){
        this.automoveis.add(automovel);
    }

    public void removeAutomovel(Automovel automovel){
        this.automoveis.remove(automovel);
    }

    public List<Orcamento> getOrcamentos() {
        return orcamentos;
    }

    public void removeOrcamento(Orcamento orcamento){
        orcamentos.remove(orcamento);
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    private void mudarStatusOrcamento(Orcamento orcamento){
        orcamento.setStatus(StatusOrcamento.INATIVO);
    }

}
