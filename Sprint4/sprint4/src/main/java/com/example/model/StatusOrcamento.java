package com.example.model;

public enum StatusOrcamento {
    ATIVO("A"),
    INATIVO("I");

    private String codigo;

    StatusOrcamento(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static StatusOrcamento getStatus(String codigo) {
        for (StatusOrcamento statusOrcamento : StatusOrcamento.values()) {
            if (statusOrcamento.getCodigo().equals(codigo)) {
                return statusOrcamento;
            }
        }
        return null;
    }
}
