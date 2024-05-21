package com.example.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrcamentoTest {

    Pedido mockPedido = new Pedido(3, new Produto("Pneu", "Aro 17", 100.50));

    @Test
    void se_adicionar_1_pedido_get_pedido_size_retorna_1() {
        Orcamento mockOrcamento = new Orcamento();
        mockOrcamento.addPedido(mockPedido);
        Assertions.assertEquals(1, mockOrcamento.getPedidos().size());
    }

    @Test
    void se_remover_pedido_get_pedido_size_retorna_0() {
        Orcamento mockOrcamento = new Orcamento();
        mockOrcamento.removePedido(mockPedido);
        Assertions.assertEquals(0, mockOrcamento.getPedidos().size());
    }

    @Test
    void se_tiver_2_pedido_com_3_quantidade_de_100_50_valor_total_igual_603() {
        Orcamento mockOrcamento = new Orcamento();
        mockOrcamento.addPedido(mockPedido);
        mockOrcamento.addPedido(mockPedido);
        Assertions.assertEquals(603, mockOrcamento.getValorTotal());
    }
}