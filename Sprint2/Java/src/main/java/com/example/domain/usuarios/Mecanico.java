package com.example.domain.usuarios;

import com.example.domain.Endereco;
import com.example.domain.Orcamento;

public class Mecanico extends MinimoInformacao {

    private String nomeMecanica;
    private double valorAPagar;

    public Mecanico(String nome, String cpf, String email, String senha, Endereco endereco, String nomeMecanica) {
        super(nome, cpf, email, senha, endereco);
        this.nomeMecanica = nomeMecanica;
    }

    public String getNomeMecanica() {
        return nomeMecanica;
    }

    public double getValorAPagar() {
        return valorAPagar;
    }

    //Um mecanico pode fazer quantos orçamento quiser porem paga um valor a cada orçamento
    @Override
    public void addOrcamento(Orcamento orcamento) {
        getOrcamentos().add(orcamento);
        valorAPagar += 5;
    }


    @Override
    public String toString() {
        return "Mecanico: " + "Nome: " + getNome() + ", Email: " + getEmail() + ", Nome da Mecanica: " + this.nomeMecanica;
    }
}
