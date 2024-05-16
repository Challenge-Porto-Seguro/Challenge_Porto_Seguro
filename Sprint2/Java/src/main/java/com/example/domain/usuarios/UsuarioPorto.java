package com.example.domain.usuarios;

import com.example.domain.Cpf;
import com.example.domain.Endereco;
import com.example.domain.Orcamento;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class UsuarioPorto extends MinimoInformacao{
    private Cpf cpf;
    private String codigoSeguro;
    private int quantidadeOrcamento;
    private LocalDate diaOrcamento;

    public UsuarioPorto(String nome, String cpf, String email, String senha, Endereco endereco, String codigoSeguro) {
        super(nome, email, senha, endereco);
        this.codigoSeguro = codigoSeguro;
        this.cpf = new Cpf(cpf);
    }

    public String getCodigoSeguro() {
        return codigoSeguro;
    }

    public int getQuantidadeOrcamento() {
        return quantidadeOrcamento;
    }

    public Cpf getCpf() {
        return cpf;
    }

    //Um Usuario Porto pode fazer ate 7 orçamentos por mês
    @Override
    public void addOrcamento(Orcamento orcamento) {
        LocalDate diaHoje = LocalDate.now();
        if(quantidadeOrcamento == 0){
            diaOrcamento = diaHoje;
        }

        int diferencaDias = (int) ChronoUnit.DAYS.between(diaOrcamento, diaHoje);
        if(quantidadeOrcamento == 7 && diferencaDias != 30){
            throw new RuntimeException("Limite de orçamento por mês atingido\nVocê poda faze outro orçamento no dia: " + diaOrcamento.getDayOfMonth() + " do mês: " + (diaOrcamento.getMonthValue() + 1));
        } else if(quantidadeOrcamento == 7) {
            quantidadeOrcamento = 0;
        }

        getOrcamentos().add(orcamento);
        quantidadeOrcamento++;
    }
    @Override
    public String toString() {
        return "Usuario Porto: " + "Nome: " + getNome() + ", Email: " + getEmail();
    }
}

