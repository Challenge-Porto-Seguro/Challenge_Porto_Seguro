package com.example.validacoes;

import java.time.LocalDate;

public class VerificaCriacaoAutomovel {

    public static void verifica(String marca, String modelo, String placa, LocalDate ano){
        if(marca.isBlank()){
            throw new RuntimeException("Marca invalida");
        }
        if(modelo.isBlank()){
            throw new RuntimeException("Modelo invalido");
        }
        if(placa.isBlank() || !placa.matches("[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")){
            throw new RuntimeException("Placa invalida");
        }
        if(ano == null || ano.isAfter(LocalDate.now())){
            throw new RuntimeException("Ano invalido");
        }
    }
}
