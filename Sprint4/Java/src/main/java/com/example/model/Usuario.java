package com.example.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario extends Login implements FazerOrcamento {

    private Cpf cpf;
    private int quantidadeOrcamento;
    private LocalDate diaUltimoOrcamento;
    private List<Automovel> automoveis = new ArrayList<>();

    public Usuario(String nome, String cpf, String email, String senha) {
        super(nome, email, senha);
        this.cpf = new Cpf(cpf);
    }

    public Usuario(String nome, String senha, String cpf) {
        super(nome, senha);
        this.cpf = new Cpf(cpf);
    }

    public String getCpf() {
        return cpf.toString();
    }

    public int getQuantidadeOrcamento() {
        return quantidadeOrcamento;
    }

    public LocalDate getDiaUltimoOrcamento() {
        return diaUltimoOrcamento;
    }

    public void addAutomovel(Automovel automovel){
        this.automoveis.add(automovel);
    }

    public void removeAutomovel(Automovel automovel){
        this.automoveis.remove(automovel);
    }

    public List<Automovel> getAutomoveis() {
        return automoveis;
    }

    //Um usuario pode fazer ate 3 orçamentos por mes
    @Override
    public void addOrcamento(Long idAutomovel, Orcamento orcamento) {
        for(Automovel automovel : automoveis){
            if(Objects.equals(automovel.getId(), idAutomovel)){
                if(quantidadeOrcamento == 0){
                    diaUltimoOrcamento = orcamento.getDiaOrcamento();
                }

                Period period = Period.between(diaUltimoOrcamento, LocalDate.now());
                int diferencaMeses = period.getMonths();
                if(quantidadeOrcamento == 3 && diferencaMeses != 1){
                    throw new RuntimeException("Limite de orçamento por mês atingido\nVocê poda faze outro orçamento no dia: " + diaUltimoOrcamento.getDayOfMonth() + " do mês: " + (diaUltimoOrcamento.getMonthValue() + 1));
                } else if(diferencaMeses == 1) {
                    quantidadeOrcamento = 0;
                    this.diaUltimoOrcamento = orcamento.getDiaOrcamento();
                }
                automovel.getDiagnostico().criarOrcamento(orcamento);
                quantidadeOrcamento++;
                break;
            }
        }

    }

    @Override
    public String toString() {
        return "Id: " + getId() + ", Nome: " + getNome() + ", Email: " + getEmail() + ", Cpf: " + getCpf() + ", Senha: " + getSenha() + " " + getQuantidadeOrcamento() + " " + getDiaUltimoOrcamento();
    }
}
