package com.example.service;

public final class AutomovelServiceFactory {

    private AutomovelServiceFactory() {
        throw new UnsupportedOperationException("Essa classe não pode ser instanciada");
    }

    public static AutomovelService getAutomovelService() {
        return new AutomovelServiceImpl();
    }
}
