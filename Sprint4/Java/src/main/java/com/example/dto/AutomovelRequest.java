package com.example.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AutomovelRequest(
        String marca,
        String modelo,
        String placa,
        @JsonAlias("dt_veiculo")
        String dataVeiculo,
        @JsonAlias("cd_pessoa")
        Long userId
) {
}
