package com.example.model.usuarios;

import com.example.enums.StatusOrcamento;
import com.example.interfaces.VerificaDados;
import com.example.model.Endereco;
import com.example.model.Orcamento;
import com.example.validacoes.ValidaInformacoesLogin;

public abstract class Login implements VerificaDados {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Endereco endereco;

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
