package com.example.dto;

public record UsuarioResponse(
        String nome,
        String email,
        String senha,
        String cpf
) {
}
