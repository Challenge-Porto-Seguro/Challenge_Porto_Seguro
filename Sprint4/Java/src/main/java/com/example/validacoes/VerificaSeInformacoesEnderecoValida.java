package com.example.validacoes;

import com.example.model.Endereco;
import com.example.model.PegaEndereceoViaApi;

public class VerificaSeInformacoesEnderecoValida {

    public static void verificaInformacoes(String rua, int numero, String cep, String bairro, String cidade, String uf){
        Endereco endereco = PegaEndereceoViaApi.pegarEndereco(cep);
        if (endereco.getCep() == null){
            throw new RuntimeException("CEP invalido");
        }
        if(!rua.equalsIgnoreCase(endereco.getRua()) ){
            throw new RuntimeException("Rua invalida");
        }
        if(!bairro.equalsIgnoreCase(endereco.getBairro()) ){
            throw new RuntimeException("Bairro invalido");
        }
        if(!cidade.equalsIgnoreCase(endereco.getCidade())){
            throw new RuntimeException("Cidade invalida");
        }
        if(!uf.equalsIgnoreCase(endereco.getUf())){
            throw new RuntimeException("Uf invalido");
        }
        if(numero < 0){
            throw new RuntimeException("numero invalido");
        }
    }
}
