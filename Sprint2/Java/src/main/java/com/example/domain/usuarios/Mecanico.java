package com.example.domain.usuarios;

import com.example.ValidaInformacoes;
import com.example.domain.Endereco;
import com.example.domain.Orcamento;

public class Mecanico extends MinimoInformacao {

    private String nomeMecanica;
    private double valorAPagar;

    public Mecanico(String nome, String cpf, String email, String senha, Endereco endereco, String nomeMecanica) {
        super(nome, cpf, email, senha, endereco);
        validaDados(nome, cpf, email, senha);
        this.nomeMecanica = nomeMecanica;
    }

    public String getNomeMecanica() {
        return nomeMecanica;
    }

    public double getValorAPagar() {
        return valorAPagar;
    }

    @Override
    public void addOrcamento(Orcamento orcamento) {
        getOrcamentos().add(orcamento);
        valorAPagar += 5;
    }

    @Override
    public void validaDados(String nome, String cpf, String email, String senha) {
        if(!ValidaInformacoes.validaNome(nome)){
            throw new RuntimeException("Nome invalido");
        }
        if(!ValidaInformacoes.validaCPF(cpf)){
            throw new RuntimeException("CPF invalido");
        }
        if(!ValidaInformacoes.validaEmail(email)){
            throw new RuntimeException("Email invalido");
        }
        if(!ValidaInformacoes.validaSenha(senha)){
            throw new RuntimeException("Senha invalida");
        }
    }

    @Override
    public String toString() {
        return "Mecanico: " + "Nome: " + getNome() + ", Email: " + getEmail() + ", Nome da Mecanica: " + this.nomeMecanica;
    }
}
