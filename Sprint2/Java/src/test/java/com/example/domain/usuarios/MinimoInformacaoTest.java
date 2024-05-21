package com.example.domain.usuarios;

import com.example.domain.Automovel;
import com.example.domain.Endereco;
import com.example.domain.Orcamento;
import com.example.enums.StatusOrcamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MinimoInformacaoTest {

    Endereco mockEndereco = new Endereco("01311-000", 1106);

    @Test
    void se_nome_invalido_validaDados_retorna_erro() {
        MinimoInformacao mockMinimoInformacao = new UsuarioComum("Pedro", "12345678909", "ph@gmail.com", "12345678", mockEndereco);
        Assertions.assertThrows(RuntimeException.class, () ->mockMinimoInformacao.validaDados("123", "ph@gmail,com", "12345678"));
    }

    @Test
    void se_email_invalido_validaDados_retorna_erro() {
        MinimoInformacao mockMinimoInformacao = new UsuarioComum("Pedro", "12345678909", "ph@gmail.com", "12345678", mockEndereco);
        Assertions.assertThrows(RuntimeException.class, () ->mockMinimoInformacao.validaDados("Pedro", "ph", "12345678"));
    }

    @Test
    void se_senha_invalido_validaDados_retorna_erro() {
        MinimoInformacao mockMinimoInformacao = new UsuarioComum("Pedro", "12345678909", "ph@gmail.com", "12345678", mockEndereco);
        Assertions.assertThrows(RuntimeException.class, () ->mockMinimoInformacao.validaDados("Pedro", "ph", "123"));
    }

    @Test
    void se_adiciona_automovel_get_automove_size_igual_1() {
        MinimoInformacao mockMinimoInformacao = new UsuarioComum("Pedro", "12345678909", "ph@gmail.com", "12345678", mockEndereco);
        Automovel mockAutomovel = new Automovel("Tesla", "model x", "AAA1234", new Date());
        mockMinimoInformacao.addAutomovel(mockAutomovel);
        Assertions.assertEquals(1, mockMinimoInformacao.getAutomoveis().size());
    }

    @Test
    void se_remove_automovel_get_automove_size_igual_0() {
        MinimoInformacao mockMinimoInformacao = new UsuarioComum("Pedro", "12345678909", "ph@gmail.com", "12345678", mockEndereco);
        Automovel mockAutomovel = new Automovel("Tesla", "model x", "AAA1234", new Date());
        mockMinimoInformacao.removeAutomovel(mockAutomovel);
        Assertions.assertEquals(0, mockMinimoInformacao.getAutomoveis().size());
    }

    @Test
    void se_adiciona_orcamento_get_orcamento_size_igual_1() {
        MinimoInformacao mockMinimoInformacao = new UsuarioComum("Pedro", "12345678909", "ph@gmail.com", "12345678", mockEndereco);
        Orcamento mockOrcamento = new Orcamento();
        mockMinimoInformacao.addOrcamento(mockOrcamento);
        Assertions.assertEquals(1, mockMinimoInformacao.getOrcamentos().size());
    }

    @Test
    void se_remove_orcamento_get_orcamento_size_igual_0() {
        MinimoInformacao mockMinimoInformacao = new UsuarioComum("Pedro", "12345678909", "ph@gmail.com", "12345678", mockEndereco);
        Orcamento mockOrcamento = new Orcamento();
        mockMinimoInformacao.removeOrcamento(mockOrcamento);
        Assertions.assertEquals(0, mockMinimoInformacao.getOrcamentos().size());
    }

    @Test
    void se_cep_igual_01311000_get_endereco_rua_igual_Avenida_Paulista() {
        MinimoInformacao mockMinimoInformacao = new UsuarioComum("Pedro", "12345678909", "ph@gmail.com", "12345678", mockEndereco);
        Assertions.assertEquals("Avenida Paulista", mockMinimoInformacao.getEndereco().getRua());
    }

    @Test
    void se_mudarStatusOrcamento_orcamento_get_status_fica_inatico() {
        MinimoInformacao mockMinimoInformacao = new UsuarioComum("Pedro", "12345678909", "ph@gmail.com", "12345678", mockEndereco);
        Orcamento mockOrcamento = new Orcamento();
        mockMinimoInformacao.mudarStatusOrcamento(mockOrcamento);
        Assertions.assertEquals(StatusOrcamento.INATIVO, mockOrcamento.getStatus());
    }
}