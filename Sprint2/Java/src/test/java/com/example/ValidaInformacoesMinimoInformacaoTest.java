package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidaInformacoesMinimoInformacaoTest {

    @Test
    void se_nome_em_branco_valida_nome_retorna_false() {
        Assertions.assertFalse(ValidaInformacoesMinimoInformacao.validaNome(""));
    }

    @Test
    void se_email_em_branco_valida_email_retorna_false() {
        Assertions.assertFalse(ValidaInformacoesMinimoInformacao.validaEmail(""));
    }

    @Test
    void se_senha_em_branco_valida_senha_retorna_false() {
        Assertions.assertFalse(ValidaInformacoesMinimoInformacao.validaSenha(""));
    }
}