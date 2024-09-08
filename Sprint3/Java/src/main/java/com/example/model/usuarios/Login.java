package com.example.model.usuarios;

import com.example.validacoes.ValidaInformacoesLogin;
import com.example.model.*;
import com.example.enums.StatusOrcamento;
import com.example.interfaces.FazerOrcamento;
import com.example.interfaces.VerificaDados;

import java.util.ArrayList;
import java.util.List;

public abstract class Login implements FazerOrcamento, VerificaDados {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Endereco endereco;
    private List<Automovel> automoveis = new ArrayList<>();


    public Login(String nome, String email, String senha) {
        validaDados(nome, email, senha);
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Automovel> getAutomoveis() {
        return automoveis;
    }

    public void alterarSenha(String email, String novaSenha){

        if(email.equals(this.email)){
            if(ValidaInformacoesLogin.validaSenha(novaSenha)){
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
        if(!ValidaInformacoesLogin.validaNome(nome)){
            throw new RuntimeException("Nome invalido");
        }
        if(!ValidaInformacoesLogin.validaEmail(email)){
            throw new RuntimeException("Email invalido");
        }
        if(!ValidaInformacoesLogin.validaSenha(senha)){
            throw new RuntimeException("Senha invalida");
        }
    }

    public void addAutomovel(Automovel automovel){
        this.automoveis.add(automovel);
    }

    public void removeAutomovel(Automovel automovel){
        this.automoveis.remove(automovel);
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
