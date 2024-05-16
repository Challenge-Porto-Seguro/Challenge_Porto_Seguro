package com.example.domain;

import com.example.enums.VerificaDiagnostico;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Diagnostico {

    public static int sequencia = 1;
    private int id;
    private String descricao;
    private Date data;
    private Date dataFinalizado;
    private VerificaDiagnostico verificador;

    public Diagnostico(String descricao) {
        id += sequencia++;
        this.descricao = descricao;
        this.data = new Date();
        this.verificador = VerificaDiagnostico.DIAGNOSTICO_NAO_RESOLVIDO;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getData(){
        return data;
    }

    public VerificaDiagnostico getVerificador() {
        return verificador;
    }

    public void diagnosticoResolvido() {
        this.verificador = VerificaDiagnostico.RESOLVIDO;
        dataFinalizado = new Date();
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataStr = sdf.format(data);
        if(verificador == VerificaDiagnostico.DIAGNOSTICO_NAO_RESOLVIDO){
            return "id: " + id +  ", descrição: " + descricao + ", data inicio: " + dataStr + ", verificador: " + verificador;
        }

        String dataFinalizadoStr = sdf.format(dataFinalizado);
        return "id: " + id
                + ", descrição: " + descricao
                + ", data inicio: " + dataStr
                + ", data finalizado: " + dataFinalizadoStr
                + ", verificador: " + verificador;
    }
}
