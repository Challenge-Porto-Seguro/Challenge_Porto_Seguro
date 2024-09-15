package com.example.model.usuarios;

import com.example.interfaces.FazerOrcamento;
import com.example.model.Automovel;
import com.example.model.Cpf;
import com.example.model.Orcamento;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    public String getCpf() {
        return cpf.toString();
    }

    public int getQuantidadeOrcamento() {
        return quantidadeOrcamento;
    }

    public LocalDate getDiaUltimoOrcamento() {
        return diaUltimoOrcamento;
    }

    public void setQuantidadeOrcamento(int quantidadeOrcamento) {
        this.quantidadeOrcamento = quantidadeOrcamento;
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
        LocalDate diaHoje = LocalDate.now();
        for(Automovel automovel : automoveis){
            if(Objects.equals(automovel.getId(), idAutomovel)){
                if(quantidadeOrcamento == 0){
                    diaUltimoOrcamento = orcamento.getDiaOrcamento();
                }

                int diferencaDias = (int) ChronoUnit.DAYS.between(diaUltimoOrcamento, diaHoje);
                if(quantidadeOrcamento == 3 && diferencaDias < 30){
                    throw new RuntimeException("Limite de orçamento por mês atingido\nVocê poda faze outro orçamento no dia: " + diaUltimoOrcamento.getDayOfMonth() + " do mês: " + (diaUltimoOrcamento.getMonthValue() + 1));
                } else if(diferencaDias >= 30) {
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
        return "Nome: " + getNome() + ", Email: " + getEmail() + ", Cpf: " + getCpf() + ", Senha: " + getSenha() + " " + getQuantidadeOrcamento() + " " + getDiaUltimoOrcamento();
    }
}
