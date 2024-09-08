package com.example.model.usuarios;

import com.example.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OficinaTest {

    Endereco mockEndereco = new Endereco("01311-000", 1106);

    @Test
    void se_cnpj_igual_a_19036613000107_get_cnpj_retorna_cnpj_formatado() {
        Oficina mockOficina = new Oficina("Mecanica Boa Viagem", "19036613000107", "bv@gmail.com", "12345678", "12345678911");
        Cnpj mockCnpj = new Cnpj("19036613000107");
        Assertions.assertEquals(mockCnpj.toString(), mockOficina.getCnpj().toString());
    }

    @Test
    void se_inscricao_estadual_igual_12345678911_get_incricao_retorna_12345678911(){
        Oficina mockOficina = new Oficina("Mecanica Boa Viagem", "19036613000107", "bv@gmail.com", "12345678", "12345678911");
        Assertions.assertEquals("12345678911", mockOficina.getInscricaoEstadual());
    }

}