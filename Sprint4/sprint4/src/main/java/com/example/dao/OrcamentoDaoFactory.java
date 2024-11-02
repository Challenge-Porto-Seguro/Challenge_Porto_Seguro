package com.example.dao;

public final class OrcamentoDaoFactory {

    private OrcamentoDaoFactory(){
        throw new UnsupportedOperationException("Essa classe não deve ser instanciada");
    }

    public static OrcamentoDao getOrcamentoDao(){
        return new OrcamentoDaoImpl();
    }
}
