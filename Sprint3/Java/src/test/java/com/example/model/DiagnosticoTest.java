package com.example.model;

import com.example.enums.VerificaDiagnostico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

class DiagnosticoTest {

    @Test
    void se_descricao_igual_seu_pnue_esta_furado_get_descricao_devolve_a_mesma_mensagem() {
        Diagnostico mockDiagnostico = new Diagnostico("seu pneu esta furado");
        Assertions.assertEquals("seu pneu esta furado", mockDiagnostico.getDescricao());
    }

    @Test
    void se_diagnostico_criado_hoje_get_data_igual_a_data_de_hoje() {
        Diagnostico mockDiagnostico = new Diagnostico("seu pneu esta furado");
        boolean igual = new Date().toString().equals(mockDiagnostico.getData().toString());
        Assertions.assertTrue(igual);
    }

    @Test
    void se_verificador_igual_a_dignostico_nao_reslvido_get_verificador_igual_a_nao_resolvido() {
        Diagnostico mockDiagnostico = new Diagnostico("seu pneu esta furado");
        Assertions.assertEquals(VerificaDiagnostico.DIAGNOSTICO_NAO_RESOLVIDO, mockDiagnostico.getVerificador());
    }

    @Test
    void se_diagnostico_resolvido_verificador_igual_a_RESOLVIDO() {
        Diagnostico mockDiagnostico = new Diagnostico("seu pneu esta furado");
        mockDiagnostico.diagnosticoResolvido();
        Assertions.assertEquals(VerificaDiagnostico.RESOLVIDO, mockDiagnostico.getVerificador());
    }

    @Test
    void se_diagnostico_resolvido_hoje_data_finalizado_igual_hoje() {
        Diagnostico mockDiagnostico = new Diagnostico("seu pneu esta furado");
        mockDiagnostico.diagnosticoResolvido();
        Assertions.assertEquals(new Date(), mockDiagnostico.getDataFinalizado());
    }

    @Test
    void se_diagnostico_nao_resolvido_get_data_finalizado_retorna_erro() {
        Diagnostico mockDiagnostico = new Diagnostico("seu pneu esta furado");
        Assertions.assertThrows(RuntimeException.class, mockDiagnostico::getDataFinalizado);
    }

    @Test
    void se_diagnostico_to_string_retorna_algumas_informacoes() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Diagnostico mockDiagnostico = new Diagnostico("seu pneu esta furado");
        String retorno = "id: " + mockDiagnostico.getId()
                + ", descrição: " + mockDiagnostico.getDescricao()
                + ", data inicio: " + sdf.format(new Date())
                + ", verificador: " + VerificaDiagnostico.DIAGNOSTICO_NAO_RESOLVIDO;
        Assertions.assertEquals(retorno, mockDiagnostico.toString());
    }
}