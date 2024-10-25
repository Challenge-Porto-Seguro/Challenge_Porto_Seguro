package com.example.service;

public class AutomovelServiceFactory {

    private AutomovelServiceFactory() {
        throw new UnsupportedOperationException("Essa classe não pode ser instanciada");
    }

    public static AutomovelService getAutomovelService() {
        return new AutomovelServiceImpl();
    }
}
