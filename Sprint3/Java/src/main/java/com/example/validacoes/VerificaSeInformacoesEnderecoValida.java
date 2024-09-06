package com.example.validacoes;

import com.example.model.Endereco;
import com.example.model.PegaEndereceoViaApi;

public class VerificaSeInformacoesEnderecoValida {

    public static void verificaInformacoes(String rua, int numero, String cep, String bairro, String cidade, String uf){
        Endereco endereco = PegaEndereceoViaApi.pegarEndereco(cep);
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
}
