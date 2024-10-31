package com.example.dto;

public record OficinaRequest(
        String nome,
        String email,
        String senha,
        String cnpj,
        String inscricaoEstadual
) {
}
