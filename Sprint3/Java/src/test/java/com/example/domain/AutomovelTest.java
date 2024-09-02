package com.example.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class AutomovelTest {

    @Test
    void se_placa_igual_123AAAA_retorna_erro(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Assertions.assertThrows(RuntimeException.class, ()-> new Automovel("Tesla", "model x", "123AAAA", sdf.parse("20/10/2005")));
    }

    @Test
    void se_ano_igual_null_retorna_erro(){
        Assertions.assertThrows(RuntimeException.class, ()-> new Automovel("Tesla", "model x", "123AAAA", null));
    }

    @Test
    void se_ano_igual_depois_de_hoje_retorna_erro(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Assertions.assertThrows(RuntimeException.class, ()-> new Automovel("Tesla", "model x", "123AAAA", sdf.parse("20/10/2025")));
    }

    @Test
    void se_modelo_em_branco_retorna_erro(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Assertions.assertThrows(RuntimeException.class, ()-> new Automovel("Tesla", "", "123AAAA", sdf.parse("20/10/2005")));
    }

    @Test
    void se_marca_em_branco_retorna_erro(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Assertions.assertThrows(RuntimeException.class, ()-> new Automovel("", "model x", "123AAAA", sdf.parse("20/10/2005")));
    }

    @Test
    void se_placa_igual_AAA1234_nao_retorna_erro() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Assertions.assertDoesNotThrow(()-> new Automovel("Tesla", "model x", "AAA1234", sdf.parse("20/10/2005")));
    }

    @Test
    void se_placa_igual_AAA1234_get_placa_retorna_AAA1234() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Automovel mockAutomovel = new Automovel("Tesla", "model x", "AAA1234", sdf.parse("20/10/2005"));
        Assertions.assertEquals("AAA1234", mockAutomovel.getPlaca());
    }

    @Test
    void se_ano_igual_20_10_2005_ano_formatado_igual_20_10_2005() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Automovel mockAutomovel = new Automovel("Tesla", "model x", "AAA1234", sdf.parse("20/10/2005"));
        Assertions.assertEquals("20/10/2005", mockAutomovel.anoFormatado());
    }


    @Test
    void se_setar_um_diagnostico_get_diagnostico_retorna_um_diagnostico() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Automovel mockAutomovel = new Automovel("Tesla", "model x", "AAA1234", sdf.parse("20/10/2005"));
        Diagnostico mockDiagnostico = new Diagnostico("Meu carro não liga");
        mockAutomovel.setDiagnostico(mockDiagnostico);
        Assertions.assertEquals(mockDiagnostico, mockAutomovel.getDiagnostico());
    }

    @Test
    void se_atualiza_diagnostico_get_diagnosticos_dever_ter_um_diagnostico() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Automovel mockAutomovel = new Automovel("Tesla", "model x", "AAA1234", sdf.parse("20/10/2005"));
        Diagnostico mockDiagnostico = new Diagnostico("Meu carro não liga");
        mockAutomovel.setDiagnostico(mockDiagnostico);
        mockAutomovel.autalizarDiagnostico();
        Assertions.assertEquals(mockDiagnostico, mockAutomovel.getDiagnosticos().getFirst());
    }
}