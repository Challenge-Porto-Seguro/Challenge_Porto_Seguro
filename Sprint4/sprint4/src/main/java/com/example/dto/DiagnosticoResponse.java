package com.example.dto;

public record DiagnosticoResponse(
        Long cd_automovel,
        String descricao,
        String dt_inicio,
        Long cd_oficina,
        String dt_fim,
        String status
) {
}
