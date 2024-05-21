package com.example.domain.usuarios;

import com.example.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioComumTest {

    Endereco mockEndereco = new Endereco("01311-000", 1106);

    @Test
    void se_cpf_igual_12345678909_get_cpf_12345678909() {
        Cpf cpf = new Cpf("12345678909");
        UsuarioComum mockUsuarioComum = new UsuarioComum("Pedro Henrique", "12345678909", "ph@gmail.com", "12345678", mockEndereco);
        Assertions.assertEquals(cpf, mockUsuarioComum.getCpf());
    }

    @Test
    void se_quantidade_orcamento_igual_a_3_e_menos_de_30_dias_add_orcamento_retorna_erro() {
        UsuarioComum mockUsuarioComum = new UsuarioComum("Pedro Henrique", "12345678909", "ph@gmail.com", "12345678", mockEndereco);
        Orcamento mockOrcamento = new Orcamento();
        mockOrcamento.addPedido(new Pedido(2, new Produto("Pneu", "Aro 16", 100.5)));
        mockUsuarioComum.addOrcamento(mockOrcamento);
        mockUsuarioComum.addOrcamento(mockOrcamento);
        mockUsuarioComum.addOrcamento(mockOrcamento);
        Assertions.assertThrows(RuntimeException.class, () -> mockUsuarioComum.addOrcamento(mockOrcamento));
    }

    @Test
    void se_quantidade_orcamento_igual_a_3_e_get_quantidade_igual_a_3() {
        UsuarioComum mockUsuarioComum = new UsuarioComum("Pedro Henrique", "12345678909", "ph@gmail.com", "12345678", mockEndereco);
        Orcamento mockOrcamento = new Orcamento();
        mockOrcamento.addPedido(new Pedido(2, new Produto("Pneu", "Aro 16", 100.5)));
        mockUsuarioComum.addOrcamento(mockOrcamento);
        mockUsuarioComum.addOrcamento(mockOrcamento);
        mockUsuarioComum.addOrcamento(mockOrcamento);
        Assertions.assertEquals(3, mockUsuarioComum.getQuantidadeOrcamento());
    }

    @Test
    void se_hoje_igual_20_05_2024_get_dia_orcamento_igual_a_hoje() {
        UsuarioComum mockUsuarioComum = new UsuarioComum("Pedro Henrique", "12345678909", "ph@gmail.com", "12345678", mockEndereco);
        Orcamento mockOrcamento = new Orcamento();
        mockOrcamento.addPedido(new Pedido(2, new Produto("Pneu", "Aro 16", 100.5)));
        mockUsuarioComum.addOrcamento(mockOrcamento);
        mockUsuarioComum.addOrcamento(mockOrcamento);
        mockUsuarioComum.addOrcamento(mockOrcamento);
        Assertions.assertEquals("2024-05-20", mockUsuarioComum.getDiaUltimoOrcamento().toString());
    }

    @Test
    void se_alterar_senha_e_email_e_nova_senha_for_valida_nao_retorna_erro(){
        UsuarioComum mockUsuarioComum = new UsuarioComum("Pedro Henrique", "12345678909", "ph@gmail.com", "12345678", mockEndereco);
        Assertions.assertDoesNotThrow(() -> mockUsuarioComum.alterarSenha("ph@gmail.com", "12345675"));
    }

    @Test
    void se_alterar_senha_e_email_ou_nova_senha_for_invalido_retorna_erro(){
        UsuarioComum mockUsuarioComum = new UsuarioComum("Pedro Henrique", "12345678909", "ph@gmail.com", "12345678", mockEndereco);
        Assertions.assertThrows(RuntimeException.class, () -> mockUsuarioComum.alterarSenha("ph@gmail.co", "12345675"));
    }

    @Test
    void se_cpf_12345678909_get_cpf_retorna_cpf_formatado(){
        Cpf cpf = new Cpf("12345678909");
        UsuarioComum mockUsuarioComum = new UsuarioComum("Pedro Henrique", "12345678909", "ph@gmail.com", "12345678", mockEndereco);
        Assertions.assertEquals(cpf.toString(), mockUsuarioComum.getCpf().toString());
    }

}