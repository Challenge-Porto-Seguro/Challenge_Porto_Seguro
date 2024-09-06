package com.example.validacoes;

public class VerificaCepValido {

    public static void verificaCep(String cep) {
        cep = cep.replaceAll("-", "");
        if(cep.length() != 8 || !cep.matches("^[0-9]+$")) {
            throw new RuntimeException("CEP Invalido!");
        }
    }
}
