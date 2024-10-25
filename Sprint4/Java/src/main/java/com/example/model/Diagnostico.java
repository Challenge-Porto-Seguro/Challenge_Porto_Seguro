package com.example.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Diagnostico {

    private Long id;
    private Long idAutomovel;
    private String descricao;
    private Date data;
    private Date dataFinalizado;
    private VerificaDiagnostico verificador;
    private Orcamento orcamento;

    public Diagnostico(String descricao) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataFinalizado() {
        if (dataFinalizado == null) {
            throw new RuntimeException("Esse diagnostico não foi finalizado");
        }
        return dataFinalizado;
    }

    public void criarOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
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
