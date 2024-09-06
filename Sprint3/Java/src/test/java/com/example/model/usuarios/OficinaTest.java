package com.example.model.usuarios;

import com.example.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OficinaTest {

    Endereco mockEndereco = new Endereco("01311-000", 1106);

    @Test
    void testa_se_orcamento_igual_a_2_valor_a_pagar_igual_a_10() {
        Orcamento mockOrcamento = new Orcamento();
        mockOrcamento.addPedido(new Pedido(2, new Produto("Pneu", "Pneu aro 17", 100)));
        Oficina mockOficina = new Oficina("Mecanica Boa Viagem", "19036613000107", "bv@gmail.com", "12345678", mockEndereco, "12345678911");
        mockOficina.addOrcamento(mockOrcamento);
        mockOficina.addOrcamento(mockOrcamento);

        Assertions.assertEquals(10, mockOficina.getValorAPagar());
    }

    @Test
    void se_cnpj_igual_a_19036613000107_get_cnpj_retorna_cnpj_formatado() {
        Oficina mockOficina = new Oficina("Mecanica Boa Viagem", "19036613000107", "bv@gmail.com", "12345678", mockEndereco, "12345678911");
        Cnpj mockCnpj = new Cnpj("19036613000107");
        Assertions.assertEquals(mockCnpj.toString(), mockOficina.getCnpj().toString());
    }

    @Test
    void se_inscricao_estadual_igual_12345678911_get_incricao_retorna_12345678911(){
        Oficina mockOficina = new Oficina("Mecanica Boa Viagem", "19036613000107", "bv@gmail.com", "12345678", mockEndereco, "12345678911");
        Assertions.assertEquals("12345678911", mockOficina.getInscricaoEstadual());
    }

}