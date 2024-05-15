package com.example.domain;

import com.example.enums.VerificaDiagnostico;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Diagnostico {

    public static int sequencia = 1;
    private int id;
    private String descricao;
    private Date data;
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

    public void setVerificador(VerificaDiagnostico verificador) {
        this.verificador = verificador;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataStr = sdf.format(data);
        return "id: " + id +  ", descrição: " + descricao + ", data: " + dataStr + ", verificador: " + verificador;
    }
}
