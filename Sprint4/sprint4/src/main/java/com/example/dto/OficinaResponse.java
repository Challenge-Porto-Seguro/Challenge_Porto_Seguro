package com.example.dto;

public record OficinaResponse(
        Long id,
        String nome,
        String email,
        String cnpj,
        String inscricaoEstadual,
        String senha,
        String rua,
        String cidade,
        String estado,
        String bairro,
        String cep,
        Integer numeroCasa
) {
}
