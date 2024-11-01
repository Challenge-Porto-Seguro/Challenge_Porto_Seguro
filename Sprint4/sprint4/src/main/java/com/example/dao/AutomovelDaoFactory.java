package com.example.dao;

public final class AutomovelDaoFactory {

    private AutomovelDaoFactory(){
        throw new UnsupportedOperationException("Essa classe não deve ser instanciada");
    }

    public static AutomovelDao createAutomovelDao() {
        return new AutomovelDaoImpl();
    }

}
