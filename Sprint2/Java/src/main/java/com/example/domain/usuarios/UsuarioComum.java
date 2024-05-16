package com.example.domain.usuarios;

import com.example.domain.Cpf;
import com.example.domain.Endereco;
import com.example.domain.Orcamento;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class UsuarioComum extends MinimoInformacao {

    private Cpf cpf;
    private int quantidadeOrcamento;
    private LocalDate diaOrcamento;

    public UsuarioComum(String nome, String cpf, String email, String senha, Endereco endereco) {
        super(nome, email, senha, endereco);
        this.cpf = new Cpf(cpf);
        quantidadeOrcamento = 0;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public int getQuantidadeOrcamento() {
        return quantidadeOrcamento;
    }

    public LocalDate getDiaOrcamento() {
        return diaOrcamento;
    }

    //Um usuario pode fazer ate 3 orçamentos por mes
    @Override
    public void addOrcamento(Orcamento orcamento) {
        LocalDate diaHoje = LocalDate.now();
        if(quantidadeOrcamento == 0){
            diaOrcamento = diaHoje;
        }

        int diferencaDias = (int) ChronoUnit.DAYS.between(diaOrcamento, diaHoje);
        if(quantidadeOrcamento == 3 && diferencaDias != 30){
            throw new RuntimeException("Limite de orçamento por mês atingido\nVocê poda faze outro orçamento no dia: " + diaOrcamento.getDayOfMonth() + " do mês: " + (diaOrcamento.getMonthValue() + 1));
        } else if(quantidadeOrcamento == 3 || diferencaDias == 30) {
            quantidadeOrcamento = 0;
        }

        getOrcamentos().add(orcamento);
        quantidadeOrcamento++;
    }

    @Override
    public String toString() {
        return "Usuario Comum: " + "Nome: " + getNome() + ", Email: " + getEmail();
    }
}
