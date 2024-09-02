package com.example.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    Produto mockProduto = new Produto("Pneu", "Aro 17", 100.50);

    @Test
    void se_setNome_igual_farol_get_nome_retorna_farol() {
        mockProduto.setNome("Farol");
        Assertions.assertEquals("Farol", mockProduto.getNome());
    }

    @Test
    void se_setDescricao_igual_modelo_sportback_get_descricao_retorna_modelo_sportback() {
        mockProduto.setDescricao("modelo Sportback");
        Assertions.assertEquals("modelo Sportback", mockProduto.getDescricao());
    }
}