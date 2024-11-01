package com.example.service;

public final class UsuarioServiceFactory {

    private UsuarioServiceFactory() {
        throw new UnsupportedOperationException("Essa classe não deve ser instanciado");
    }

    public static UsuarioService getUsuarioService() {
        return new UsuarioServiceImpl();
    }
}
