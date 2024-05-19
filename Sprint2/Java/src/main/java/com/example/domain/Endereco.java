package com.example.domain;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Endereco {
    @SerializedName("logradouro")
    private String rua;
    private int numero;
    private String cep;
    private String bairro;
    @SerializedName("localidade")
    private String cidade;
    private String uf;

    public Endereco(String rua, int numero, String cep, String bairro, String cidade, String uf) {
        verificaSeInformacoesEstaoCerta(rua, numero, cep, bairro, cidade, uf);
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    public Endereco(String cep, int numero) {

        Endereco endereco = pegaInformacoesEnderecoPorCep(cep);
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

    private void verificaSeInformacoesEstaoCerta(String rua, int numero, String cep, String bairro, String cidade, String uf){

        Endereco endereco = pegaInformacoesEnderecoPorCep(cep);
        if (endereco.cep == null){
            throw new RuntimeException("CEP invalido");
        }
        if(!rua.equalsIgnoreCase(endereco.rua) ){
            throw new RuntimeException("Rua invalida");
        }
        if(!bairro.equalsIgnoreCase(endereco.bairro) ){
            throw new RuntimeException("Bairro invalido");
        }
        if(!cidade.equalsIgnoreCase(endereco.cidade)){
            throw new RuntimeException("Cidade invalida");
        }
        if(!uf.equalsIgnoreCase(endereco.uf)){
            throw new RuntimeException("Uf invalido");
        }
        if(numero < 0){
            throw new RuntimeException("numero invalido");
        }


    }

    private Endereco pegaInformacoesEnderecoPorCep(String cep){
        verificaSeCepEValido(cep);
        try {
            String enderecoPesquisa = "http://viacep.com.br/ws/" + cep + "/json/";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(enderecoPesquisa))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            Gson gson = new Gson();

            return gson.fromJson(json, Endereco.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
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

    public String getUf() {
        return uf;
    }

}
