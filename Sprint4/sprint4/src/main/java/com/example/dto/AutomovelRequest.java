package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AutomovelRequest(
        String marca,
        String modelo,
        String placa,
        @JsonProperty("dt_veiculo")
        String dataVeiculo,
        @JsonProperty("cd_pessoa")
        Long userId
) {
}
