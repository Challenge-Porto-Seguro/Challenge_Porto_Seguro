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
        Usuario mockUsuario = new Usuario("Pedro Henrique", "12345678909", "ph@gmail.com", "12345678");
        Assertions.assertEquals(cpf.toString(), mockUsuario.getCpf());
    }

    @Test
    void se_alterar_senha_e_email_e_nova_senha_for_valida_nao_retorna_erro(){
        Usuario mockUsuario = new Usuario("Pedro Henrique", "12345678909", "ph@gmail.com", "12345678");
        Assertions.assertDoesNotThrow(() -> mockUsuario.alterarSenha("ph@gmail.com", "12345675"));
    }

    @Test
    void se_alterar_senha_e_email_ou_nova_senha_for_invalido_retorna_erro(){
        Usuario mockUsuario = new Usuario("Pedro Henrique", "12345678909", "ph@gmail.com", "12345678");
        Assertions.assertThrows(RuntimeException.class, () -> mockUsuario.alterarSenha("ph@gmail.co", "12345675"));
    }

    @Test
    void se_cpf_12345678909_get_cpf_retorna_cpf_formatado(){
        Cpf cpf = new Cpf("12345678909");
        Usuario mockUsuario = new Usuario("Pedro Henrique", "12345678909", "ph@gmail.com", "12345678");
        Assertions.assertEquals(cpf.toString(), mockUsuario.getCpf());
    }

}