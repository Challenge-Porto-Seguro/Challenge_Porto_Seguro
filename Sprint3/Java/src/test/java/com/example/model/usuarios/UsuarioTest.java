package com.example.model.usuarios;

import com.example.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class UsuarioTest {

    Endereco mockEndereco = new Endereco("01311-000", 1106);

    @Test
    void se_cpf_igual_12345678909_get_cpf_12345678909() {
        Cpf cpf = new Cpf("12345678909");
        Usuario mockUsuario = new Usuario("Pedro Henrique", "12345678909", "ph@gmail.com", "12345678", mockEndereco);
        Assertions.assertEquals(cpf, mockUsuario.getCpf());
    }

    @Test
    void se_quantidade_orcamento_igual_a_3_e_menos_de_30_dias_add_orcamento_retorna_erro() {
        Usuario mockUsuario = new Usuario("Pedro Henrique", "12345678909", "ph@gmail.com", "12345678", mockEndereco);
        Orcamento mockOrcamento = new Orcamento();
        mockOrcamento.addPedido(new Pedido(2, new Produto("Pneu", "Aro 16", 100.5)));
        mockUsuario.addOrcamento(mockOrcamento);
        mockUsuario.addOrcamento(mockOrcamento);
        mockUsuario.addOrcamento(mockOrcamento);
        Assertions.assertThrows(RuntimeException.class, () -> mockUsuario.addOrcamento(mockOrcamento));
    }

    @Test
    void se_quantidade_orcamento_igual_a_3_e_get_quantidade_igual_a_3() {
        Usuario mockUsuario = new Usuario("Pedro Henrique", "12345678909", "ph@gmail.com", "12345678", mockEndereco);
        Orcamento mockOrcamento = new Orcamento();
        mockOrcamento.addPedido(new Pedido(2, new Produto("Pneu", "Aro 16", 100.5)));
        mockUsuario.addOrcamento(mockOrcamento);
        mockUsuario.addOrcamento(mockOrcamento);
        mockUsuario.addOrcamento(mockOrcamento);
        Assertions.assertEquals(3, mockUsuario.getQuantidadeOrcamento());
    }

    @Test
    void se_hoje_igual_20_05_2024_get_dia_orcamento_igual_a_hoje() {
        Usuario mockUsuario = new Usuario("Pedro Henrique", "12345678909", "ph@gmail.com", "12345678", mockEndereco);
        Orcamento mockOrcamento = new Orcamento();
        mockOrcamento.addPedido(new Pedido(2, new Produto("Pneu", "Aro 16", 100.5)));
        mockUsuario.addOrcamento(mockOrcamento);
        mockUsuario.addOrcamento(mockOrcamento);
        mockUsuario.addOrcamento(mockOrcamento);
        Assertions.assertEquals(LocalDate.now().toString(), mockUsuario.getDiaUltimoOrcamento().toString());
    }

    @Test
    void se_alterar_senha_e_email_e_nova_senha_for_valida_nao_retorna_erro(){
        Usuario mockUsuario = new Usuario("Pedro Henrique", "12345678909", "ph@gmail.com", "12345678", mockEndereco);
        Assertions.assertDoesNotThrow(() -> mockUsuario.alterarSenha("ph@gmail.com", "12345675"));
    }

    @Test
    void se_alterar_senha_e_email_ou_nova_senha_for_invalido_retorna_erro(){
        Usuario mockUsuario = new Usuario("Pedro Henrique", "12345678909", "ph@gmail.com", "12345678", mockEndereco);
        Assertions.assertThrows(RuntimeException.class, () -> mockUsuario.alterarSenha("ph@gmail.co", "12345675"));
    }

    @Test
    void se_cpf_12345678909_get_cpf_retorna_cpf_formatado(){
        Cpf cpf = new Cpf("12345678909");
        Usuario mockUsuario = new Usuario("Pedro Henrique", "12345678909", "ph@gmail.com", "12345678", mockEndereco);
        Assertions.assertEquals(cpf.toString(), mockUsuario.getCpf().toString());
    }

}