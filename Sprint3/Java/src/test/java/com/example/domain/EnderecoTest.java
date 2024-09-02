package com.example.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EnderecoTest {

    Endereco nockEndereco = new Endereco("01311-000", 1106);

    @Test
    void testa_se_cep_for_01311000_rua_igual_avenida_paulista() {
        Assertions.assertEquals("Avenida Paulista", nockEndereco.getRua());
    }

    @Test
    void testa_se_cep_for_01311000_e_numero_for_1106_numero_igual_1106() {
        Assertions.assertEquals(1106, nockEndereco.getNumero());
    }

    @Test
    void testa_se_cep_for_01311000_cep_igual_01311000(){
        Assertions.assertEquals("01311-000", nockEndereco.getCep());
    }

    @Test
    void testa_se_cep_for_01311000_bairro_igual_Bela_Vista() {
        Assertions.assertEquals("Bela Vista", nockEndereco.getBairro());
    }

    @Test
    void testa_se_cep_for_01311000_cidade_igual_Sao_Paulo() {
        Assertions.assertEquals("São Paulo", nockEndereco.getCidade());
    }

    @Test
    void testa_se_cep_for_01311000_uf_igual_sp() {
        Assertions.assertEquals("SP", nockEndereco.getUf());
    }

    @Test
    void testa_se_cep_for_01311000_uf_igual_pa_retorna_erro() {
        Assertions.assertThrows(RuntimeException.class,()-> new Endereco("Avenida Paulista", 1106, "01311-000", "Bela Vista", "São Paulo", "pa"));
    }

    @Test
    void testa_se_cep_for_01311000_e_todas_iformacoes_corretas_endereco_criado(){
        Assertions.assertDoesNotThrow(()-> new Endereco("Avenida Paulista", 1106, "01311-000", "Bela Vista", "São Paulo", "sp"));
    }

}