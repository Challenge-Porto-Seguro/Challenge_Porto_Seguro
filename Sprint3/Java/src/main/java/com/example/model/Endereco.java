package com.example.model;

import com.example.validacoes.VerificaSeInformacoesEnderecoValida;
import com.google.gson.annotations.SerializedName;

public class Endereco {
    private Long id;
    private Long idUsuario;
    @SerializedName("logradouro")
    private String rua;
    private int numero;
    private String cep;
    private String bairro;
    @SerializedName("localidade")
    private String cidade;
    private String uf;

    public Endereco(String rua, int numero, String cep, String bairro, String cidade, String uf) {
        VerificaSeInformacoesEnderecoValida.verificaInformacoes(rua, numero, cep, bairro, cidade, uf);
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    public Endereco(String cep, int numero) {

        Endereco endereco = PegaEndereceoViaApi.pegarEndereco(cep);
        if(endereco.cep == null) {
            throw new RuntimeException("CEP invalido");
        }
        this.cep = endereco.cep;
        this.rua = endereco.rua;
        this.bairro = endereco.bairro;
        this.cidade = endereco.cidade;
        this.uf = endereco.uf;
        this.numero = numero;
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

    public String getUf() {
        return uf;
    }

}
