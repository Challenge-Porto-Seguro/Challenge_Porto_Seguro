package com.example.service;

public final class OficinaServiceFactory {

    private OficinaServiceFactory() {
        throw new UnsupportedOperationException("Essa classe não deve ser instanciado");
    }

    public static OficinaService getOficinaService() {
        return new OficinaServiceImpl();
    }
}
