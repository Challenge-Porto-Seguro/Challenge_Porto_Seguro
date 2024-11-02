package com.example.dao;

public class ItensOrcamentoDaoFactory {

    private ItensOrcamentoDaoFactory(){
        throw new UnsupportedOperationException("Essa classe não deve ser instanciada");
    }

    public static ItensOrcamentoDao getItensOrcamentoDao(){
        return new ItensOrcamentoDaoImpl();
    }

}
