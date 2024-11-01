package com.example.dao;

public class DiagnosticoDaoFactory {

    private DiagnosticoDaoFactory(){
        throw new UnsupportedOperationException("Essa classe não deve ser instanciada");
    }

    public static DiagnosticoDao createDiagnosticoDao() {
        return new DiagnosticoDaoImpl();
    }
}
