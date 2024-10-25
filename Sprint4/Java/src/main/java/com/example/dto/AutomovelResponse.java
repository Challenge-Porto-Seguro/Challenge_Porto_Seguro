package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AutomovelResponse(
        Long id,
        String marca,
        String modelo,
        String placa,
        @JsonProperty("dt_veiculo")
        String dataVeiculo,
        @JsonProperty("user_id")
        Long userId
) {
}
