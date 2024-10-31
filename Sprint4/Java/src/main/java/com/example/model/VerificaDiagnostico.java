package com.example.model;

public enum VerificaDiagnostico {

    DIAGNOSTICO_NAO_RESOLVIDO("A"),
    RESOLVIDO("I");

    private String codigo;

    VerificaDiagnostico(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static VerificaDiagnostico getDiagnostico(String codigo) {
        for (VerificaDiagnostico diagnostico : VerificaDiagnostico.values()) {
            if (diagnostico.getCodigo().equals(codigo)) {
                return diagnostico;
            }
        }
        return null;
    }
}
