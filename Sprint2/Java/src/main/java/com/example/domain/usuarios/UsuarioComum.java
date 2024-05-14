package com.example.domain.usuarios;

import com.example.ValidaInformacoes;
import com.example.domain.Endereco;
import com.example.domain.Orcamento;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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

    public LocalDate getDiaOrcamento() {
        return diaOrcamento;
    }

    @Override
    public void addOrcamento(Orcamento orcamento) {
        LocalDate diaHoje = LocalDate.now();
        if(quantidadeOrcamento == 0){
            diaOrcamento = diaHoje;
        }

        int diferencaDias = (int) ChronoUnit.DAYS.between(diaOrcamento, diaHoje);
        if(quantidadeOrcamento == 3 && diferencaDias != 30){
            throw new RuntimeException("Limite de orçamento por mês atingido\nVocê poda faze outro orçamento no dia: " + diaOrcamento.getDayOfMonth() + " do mês: " + (diaOrcamento.getMonthValue() + 1));
        } else if(quantidadeOrcamento == 3) {
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

    @Override
    public String toString() {
        return "Usuario Comum: " + "Nome: " + getNome() + ", Email: " + getEmail();
    }
}
