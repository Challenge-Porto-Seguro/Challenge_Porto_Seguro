package com.example.dao;

public class OficinaDaoFactory {

    private OficinaDaoFactory(){
        throw new UnsupportedOperationException("Essa classe não deve ser instanciada");
    }

    public static OficinaDao getOficinaDao(){
        return new OficinaDaoImpl();
    }
}
