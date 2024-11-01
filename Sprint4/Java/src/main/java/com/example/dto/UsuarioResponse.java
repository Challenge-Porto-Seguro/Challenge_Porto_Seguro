package com.example.dto;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        String cpf,
        String senha,
        String rua,
        String cidade,
        String estado,
        String bairro,
        String cep,
        Integer numeroCasa
) {
}
