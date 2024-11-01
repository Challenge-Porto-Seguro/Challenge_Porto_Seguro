package com.example.service;

public final class EnderecoServiceFactory {

    private EnderecoServiceFactory() {
        throw new UnsupportedOperationException("Essa classe não deve ser instanciado");
    }

    public static EnderecoService getEnderecoService() {
        return new EnderecoServiceImpl();
    }
}
