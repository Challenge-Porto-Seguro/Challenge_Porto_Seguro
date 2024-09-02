package com.example.domain.usuarios;

import com.example.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioPortoTest {

    Endereco mockEndereco = new Endereco("01311-000", 1106);

    @Test
    void se_codigo_seguro_igual_1_get_codigo_seguro_igual_a_1() {
        UsuarioPorto mockUsuarioPorto = new UsuarioPorto("Pedro Henrique", "12345678909", "ph@gmail.com", "12345678", mockEndereco, "1");
        Assertions.assertEquals("1", mockUsuarioPorto.getCodigoSeguro());
    }

    @Test
    void se_cpf_igual_12345678909_get_cpf_12345678909() {
        Cpf cpf = new Cpf("12345678909");
        UsuarioPorto mockUsuarioPorto = new UsuarioPorto("Pedro Henrique", "12345678909", "ph@gmail.com", "12345678", mockEndereco, "1");
        Assertions.assertEquals(cpf, mockUsuarioPorto.getCpf());
    }

    @Test
    void se_quantidade_orcamento_igual_a_7_e_menos_de_30_dias_add_orcamento_retorna_erro() {
        UsuarioPorto mockUsuarioPorto = new UsuarioPorto("Pedro Henrique", "12345678909", "ph@gmail.com", "12345678", mockEndereco, "1");
        Orcamento mockOrcamento = new Orcamento();
        mockOrcamento.addPedido(new Pedido(2, new Produto("Pneu", "Aro 16", 100.5)));
        mockUsuarioPorto.addOrcamento(mockOrcamento);
        mockUsuarioPorto.addOrcamento(mockOrcamento);
        mockUsuarioPorto.addOrcamento(mockOrcamento);
        mockUsuarioPorto.addOrcamento(mockOrcamento);
        mockUsuarioPorto.addOrcamento(mockOrcamento);
        mockUsuarioPorto.addOrcamento(mockOrcamento);
        mockUsuarioPorto.addOrcamento(mockOrcamento);
        Assertions.assertThrows(RuntimeException.class, () -> mockUsuarioPorto.addOrcamento(mockOrcamento));
    }

    @Test
    void se_quantidade_orcamento_igual_a_3_e_get_quantidade_igual_a_3() {
        UsuarioPorto mockUsuarioPorto = new UsuarioPorto("Pedro Henrique", "12345678909", "ph@gmail.com", "12345678", mockEndereco, "1");
        Orcamento mockOrcamento = new Orcamento();
        mockOrcamento.addPedido(new Pedido(2, new Produto("Pneu", "Aro 16", 100.5)));
        mockUsuarioPorto.addOrcamento(mockOrcamento);
        mockUsuarioPorto.addOrcamento(mockOrcamento);
        mockUsuarioPorto.addOrcamento(mockOrcamento);
        Assertions.assertEquals(3, mockUsuarioPorto.getQuantidadeOrcamento());
    }

    @Test
    void se_hoje_igual_20_05_2024_get_dia_orcamento_igual_a_hoje() {
        UsuarioPorto mockUsuarioPorto = new UsuarioPorto("Pedro Henrique", "12345678909", "ph@gmail.com", "12345678", mockEndereco, "1");
        Orcamento mockOrcamento = new Orcamento();
        mockOrcamento.addPedido(new Pedido(2, new Produto("Pneu", "Aro 16", 100.5)));
        mockUsuarioPorto.addOrcamento(mockOrcamento);
        mockUsuarioPorto.addOrcamento(mockOrcamento);
        mockUsuarioPorto.addOrcamento(mockOrcamento);
        Assertions.assertEquals(LocalDate.now().toString(), mockUsuarioPorto.getDiaUltimoOrcamento().toString());
    }


}