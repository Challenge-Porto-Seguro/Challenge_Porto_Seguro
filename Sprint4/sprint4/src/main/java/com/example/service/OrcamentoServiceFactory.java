package com.example.service;

public final class OrcamentoServiceFactory {

    private OrcamentoServiceFactory() {
        throw new UnsupportedOperationException("Essa classe não deve ser instanciado");
    }

    public static OrcamentoService getOrcamentoService() {
        return new OrcamentoServiceImpl();
    }
}
