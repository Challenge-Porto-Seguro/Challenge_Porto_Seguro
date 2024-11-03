package com.example.dto;

public record DiagnosticoResponse(
        Long id,
        Long cd_automovel,
        String marca,
        String descricao,
        String dt_inicio,
        Long cd_oficina,
        String nomeOficina,
        String dt_fim,
        String status
) {
}
