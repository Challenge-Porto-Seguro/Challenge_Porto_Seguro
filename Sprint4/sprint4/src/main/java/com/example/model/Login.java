package com.example.model;

import com.example.validacoes.ValidaInformacoesLogin;

public abstract class Login implements VerificaDados {

    private Long id;
    private String nome;
    private String email;
    private String senha;

    public Login(String nome, String email, String senha) {
        validaDados(nome, email, senha);
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Login(String nome, String senha) {
        setNome(nome);
        setSenha(senha);
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

    public void setNome(String nome) {
        if(ValidaInformacoesLogin.validaNome(nome)){
            this.nome = nome;
        } else {
            throw new RuntimeException("Nome invalido");
        }

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (ValidaInformacoesLogin.validaEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email invalido");
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String novaSenha){
        if (ValidaInformacoesLogin.validaSenha(novaSenha)) {
            senha = novaSenha;
        } else {
            throw new RuntimeException("senha esta invalida");
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

}
