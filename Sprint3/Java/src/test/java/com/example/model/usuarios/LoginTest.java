package com.example.model.usuarios;

import com.example.model.Automovel;
import com.example.model.Endereco;
import com.example.model.Orcamento;
import com.example.enums.StatusOrcamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

class LoginTest {

    Endereco mockEndereco = new Endereco("01311-000", 1106);

    @Test
    void se_nome_invalido_validaDados_retorna_erro() {
        Login mockLogin = new Usuario("Pedro", "12345678909", "ph@gmail.com", "12345678");
        Assertions.assertThrows(RuntimeException.class, () -> mockLogin.validaDados("123", "ph@gmail,com", "12345678"));
    }

    @Test
    void se_email_invalido_validaDados_retorna_erro() {
        Login mockLogin = new Usuario("Pedro", "12345678909", "ph@gmail.com", "12345678");
        Assertions.assertThrows(RuntimeException.class, () -> mockLogin.validaDados("Pedro", "ph", "12345678"));
    }

    @Test
    void se_senha_invalido_validaDados_retorna_erro() {
        Login mockLogin = new Usuario("Pedro", "12345678909", "ph@gmail.com", "12345678");
        Assertions.assertThrows(RuntimeException.class, () -> mockLogin.validaDados("Pedro", "ph", "123"));
    }

    @Test
    void se_cep_igual_01311000_get_endereco_rua_igual_Avenida_Paulista() {
        Login mockLogin = new Usuario("Pedro", "12345678909", "ph@gmail.com", "12345678");
        mockLogin.setEndereco(mockEndereco);
        Assertions.assertEquals("Avenida Paulista", mockLogin.getEndereco().getRua());
    }

    @Test
    void se_mudarStatusOrcamento_orcamento_get_status_fica_inatico() {
        Login mockLogin = new Usuario("Pedro", "12345678909", "ph@gmail.com", "12345678");
        Orcamento mockOrcamento = new Orcamento();
        mockLogin.mudarStatusOrcamento(mockOrcamento);
        Assertions.assertEquals(StatusOrcamento.INATIVO, mockOrcamento.getStatus());
    }
}