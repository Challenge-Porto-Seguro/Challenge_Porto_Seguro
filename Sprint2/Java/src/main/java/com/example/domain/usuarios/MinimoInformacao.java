package com.example.domain.usuarios;

import com.example.FazerOrcamento;
import com.example.ValidaInformacoesUsuario;
import com.example.domain.*;
import com.example.enums.StatusOrcamento;

import java.util.ArrayList;
import java.util.List;

public abstract class MinimoInformacao implements FazerOrcamento, VerificaDados {

    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private Endereco endereco;
    private List<Automovel> automoveis = new ArrayList<>();
    private List<Orcamento> orcamentos = new ArrayList<>();

    public MinimoInformacao(String nome, String cpf, String email, String senha, Endereco endereco) {
        validaDados(nome, cpf, email, senha);
        this.nome = nome;
        this.cpf = formataCpf(cpf);
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getCpf() {
        return cpf;
    }

    public List<Automovel> getAutomoveis() {
        return automoveis;
    }

    private String formataCpf(String cpf) {
        return cpf.replace(".", "").replace("-", "");
    }
    public void alterarSenha(String cpf, String novaSenha){
        String cpfFormatado = formataCpf(cpf);

        if(cpfFormatado.equals(this.cpf)){
            if(ValidaInformacoesUsuario.validaSenha(novaSenha)){
                this.senha = novaSenha;
                System.out.println("Senha alterada com sucesso!");
            } else {
                throw new RuntimeException("Nova senha esta invalida");
            }
        } else {
            throw new RuntimeException("Não foi possivel alterar senha! Pois o cpf esta invalido");
        }
    }

    @Override
    public void validaDados(String nome, String cpf, String email, String senha) {
        if(!ValidaInformacoesUsuario.validaNome(nome)){
            throw new RuntimeException("Nome invalido");
        }
        if(!ValidaInformacoesUsuario.validaCPF(cpf)){
            throw new RuntimeException("CPF invalido");
        }
        if(!ValidaInformacoesUsuario.validaEmail(email)){
            throw new RuntimeException("Email invalido");
        }
        if(!ValidaInformacoesUsuario.validaSenha(senha)){
            throw new RuntimeException("Senha invalida");
        }
    }

    public void addAutomovel(Automovel automovel){
        this.automoveis.add(automovel);
    }

    public void removeAutomovel(Automovel automovel){
        this.automoveis.remove(automovel);
    }

    public List<Orcamento> getOrcamentos() {
        return orcamentos;
    }

    public void removeOrcamento(Orcamento orcamento){
        orcamentos.remove(orcamento);
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
