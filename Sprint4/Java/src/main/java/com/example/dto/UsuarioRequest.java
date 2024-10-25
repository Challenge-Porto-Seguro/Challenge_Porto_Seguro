package com.example.dto;

public record UsuarioRequest(
        String nome,
        String email,
        String senha,
        String cpf
) {
}
