package com.example;

import com.example.validacoes.ValidaInformacoesLogin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValidaInformacoesLoginTest {

    @Test
    void se_nome_em_branco_valida_nome_retorna_false() {
        Assertions.assertFalse(ValidaInformacoesLogin.validaNome(""));
    }

    @Test
    void se_email_em_branco_valida_email_retorna_false() {
        Assertions.assertFalse(ValidaInformacoesLogin.validaEmail(""));
    }

    @Test
    void se_senha_em_branco_valida_senha_retorna_false() {
        Assertions.assertFalse(ValidaInformacoesLogin.validaSenha(""));
    }
}