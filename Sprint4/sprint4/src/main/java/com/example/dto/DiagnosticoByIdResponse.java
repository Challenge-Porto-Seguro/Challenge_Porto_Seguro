package com.example.dto;

import java.util.List;

public record DiagnosticoByIdResponse(
        Long id,
        Long cd_automovel,
        String descricao,
        String dt_inicio,
        Long cd_oficina,
        String dt_fim,
        String status,
        List<ItensProdutoDto> produtos,
        Double valorTotal

) {
}
