package com.example.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PedidoTest {

    Pedido mockPedido = new Pedido(3, new Produto("Pneu", "Aro 17", 100.50));

    @Test
    void se_quantidade_igual_a_3_getQuantidade_retorna_3() {
        Assertions.assertEquals(3, mockPedido.getQuantidade());
    }

    @Test
    void se_nome_de_produto_igual_a_pneu_getProduto_nome_retorna_pneu() {
        Assertions.assertEquals("Pneu", mockPedido.getProduto().getNome());
    }
}