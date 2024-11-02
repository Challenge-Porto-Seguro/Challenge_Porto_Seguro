package com.example.dto;

public record DiagnosticoRequest(
        Long cd_automovel,
        String descricao,
        String dt_inicio,
        Long cd_oficina,
        String peca,
        Double preco,
        int quantidade
) {
}
