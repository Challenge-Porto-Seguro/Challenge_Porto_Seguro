package com.example.service;

public final class ItensOrcamentoServiceFactory {

    private ItensOrcamentoServiceFactory() {
        throw new UnsupportedOperationException("Essa classe não deve ser instanciado");
    }

    public static ItensOrcamentoService getItensOrcamentoService() {
        return new ItensOrcamentoServiceImpl();
    }
}
