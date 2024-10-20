package com.example.validacoes;

import java.util.Date;

public class VerificaCriacaoAutomovel {

    public static void verifica(String marca, String modelo, String placa, Date ano){
        if(marca.isBlank()){
            throw new RuntimeException("Marca invalida");
        }
        if(modelo.isBlank()){
            throw new RuntimeException("Modelo invalido");
        }
        if(placa.isBlank() || !placa.matches("[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")){
            throw new RuntimeException("Placa invalida");
        }
        if(ano == null || ano.after(new Date())){
            throw new RuntimeException("Ano invalido");
        }
    }
}
