package com.example.dto;

public record OficinaResponse(
        Long id,
        String nome,
        String email,
        String cnpj,
        String inscricaoEstadual,
        String senha
) {
}
