package com.example.domain;

public class Endereco {

    private String rua;
    private int numero;
    private String cep;
    private String bairro;
    private String cidade;
    private String estado;

    public Endereco(String rua, int numero, String cep, String bairro, String cidade, String estado) {
        verificaSeCepEValido(cep);
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    private void verificaSeCepEValido(String cep) {
        cep = cep.replaceAll("-", "");
        if(cep.length() != 8 || !cep.matches("^[0-9]+$")) {
            throw new RuntimeException("CEP Invalido!");
        }
    }

    public String getRua() {
        return rua;
    }

    public int getNumero() {
        return numero;
    }

    public String getCep() {
        return cep;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }
}
