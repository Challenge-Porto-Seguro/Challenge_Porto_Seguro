package com.example.dto;

public record UsuarioUpdateRequest(
        String nome,
        String senha,
        String cpf
) {
}
