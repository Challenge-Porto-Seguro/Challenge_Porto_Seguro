package com.example.service;

public class DiagnosticoServiceFactory {

    private DiagnosticoServiceFactory() {
        throw new UnsupportedOperationException("Essa classe não pode ser instanciada");
    }

    public static DiagnosticoService getDiagnosticoService() {
        return new DiagnosticoServiceImpl();
    }


}
