package com.example.domain;


import org.junit.Assert;
import org.junit.Test;

public class EnderecoTest {

    Endereco nockEndereco = new Endereco("01311-000", 1106);

    @Test
    public void testa_se_cep_for_01311000_rua_igual_avenida_paulista() {
        Assert.assertEquals("Avenida Paulista", nockEndereco.getRua());
    }

    @Test
    public void testa_se_cep_for_01311000_e_numero_for_1106_numero_igual_1106() {
        Assert.assertEquals(1106, nockEndereco.getNumero());
    }

    @Test
    public void testa_se_cep_for_01311000_cep_igual_01311000() {
        Assert.assertEquals("01311-000", nockEndereco.getCep());
    }

    @Test
    public void testa_se_cep_for_01311000_bairro_igual_Bela_Vista() {
        Assert.assertEquals("Bela Vista", nockEndereco.getBairro());
    }

    @Test
    public void testa_se_cep_for_01311000_cidade_igual_Sao_Paulo() {
        Assert.assertEquals("São Paulo", nockEndereco.getCidade());
    }

    @Test
    public void testa_se_cep_for_01311000_uf_igual_sp() {
        Assert.assertEquals("SP", nockEndereco.getUf());
    }

}