package com.example.dao;

public class AutomovelDaoFactory {

    private AutomovelDaoFactory(){
        throw new UnsupportedOperationException("Essa classe não deve ser instanciada");
    }

    public static AutomovelDao createAutomovelDao() {
        return new AutomovelDaoImpl();
    }

}
