package com.example.domain.usuarios;

import com.example.interfaces.FazerOrcamento;
import com.example.ValidaInformacoesMinimoInformacao;
import com.example.domain.*;
import com.example.enums.StatusOrcamento;
import com.example.interfaces.VerificaDados;

import java.util.ArrayList;
import java.util.List;

public abstract class MinimoInformacao implements FazerOrcamento, VerificaDados {

    private String nome;
    private String email;
    private String senha;
    private Endereco endereco;
    private List<Automovel> automoveis = new ArrayList<>();
    private List<Orcamento> orcamentos = new ArrayList<>();

    public MinimoInformacao(String nome, String email, String senha, Endereco endereco) {
        validaDados(nome, email, senha);
        this.nome = nome;
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

    public List<Automovel> getAutomoveis() {
        return automoveis;
    }

    public void alterarSenha(String email, String novaSenha){

        if(email.equals(this.email)){
            if(ValidaInformacoesMinimoInformacao.validaSenha(novaSenha)){
                senha = novaSenha;
                System.out.println("Senha alterada com sucesso!");
            } else {
                throw new RuntimeException("Nova senha esta invalida");
            }
        } else {
            throw new RuntimeException("Não foi possivel alterar senha! Pois não existe Usuario com esse email!");
        }
    }

    @Override
    public void validaDados(String nome, String email, String senha) {
        if(!ValidaInformacoesMinimoInformacao.validaNome(nome)){
            throw new RuntimeException("Nome invalido");
        }
        if(!ValidaInformacoesMinimoInformacao.validaEmail(email)){
            throw new RuntimeException("Email invalido");
        }
        if(!ValidaInformacoesMinimoInformacao.validaSenha(senha)){
            throw new RuntimeException("Senha invalida");
        }
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

    public void mudarStatusOrcamento(Orcamento orcamento){
        orcamento.setStatus(StatusOrcamento.INATIVO);
    }

}
