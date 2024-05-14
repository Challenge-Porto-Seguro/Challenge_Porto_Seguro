package com.example.domain.usuarios;

import com.example.ValidaInformacoes;
import com.example.domain.Endereco;
import com.example.domain.Orcamento;

import java.time.LocalDate;

public class UsuarioComum extends MinimoInformacao {

    private int quantidadeOrcamento;
    private LocalDate diaOrcamento;

    public UsuarioComum(String nome, String cpf, String email, String senha, Endereco endereco) {
        super(nome, cpf, email, senha, endereco);
        validaDados(nome, cpf, email, senha);
        quantidadeOrcamento = 0;
    }

    public int getQuantidadeOrcamento() {
        return quantidadeOrcamento;
    }

    @Override
    public void addOrcamento(Orcamento orcamento) {
        if(quantidadeOrcamento == 1){
            diaOrcamento = LocalDate.now();
        }
        if(quantidadeOrcamento == 3 && LocalDate.now().getDayOfMonth() - diaOrcamento.getDayOfMonth() != 0){
            throw new RuntimeException("Limite de orçamento por mês atingido");
        } else if(quantidadeOrcamento == 3 && LocalDate.now().getDayOfMonth() - diaOrcamento.getDayOfMonth() == 0) {
            quantidadeOrcamento = 0;
        }

        getOrcamentos().add(orcamento);
        quantidadeOrcamento++;
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
}
