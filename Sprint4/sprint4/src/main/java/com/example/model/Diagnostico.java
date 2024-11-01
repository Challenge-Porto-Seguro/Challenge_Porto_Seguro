package com.example.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Diagnostico {

    private Long id;
    private Automovel automovel;
    private String descricao;
    private LocalDate data;
    private LocalDate dataFinalizado;
    private VerificaDiagnostico status;
    private Oficina oficina;

    public Diagnostico(String descricao, LocalDate data, VerificaDiagnostico status) {
        this.descricao = descricao;
        this.data = data;
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getData(){
        return data;
    }

    public VerificaDiagnostico getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataFinalizado() {
        if (dataFinalizado == null) {
            throw new RuntimeException("Esse diagnostico não foi finalizado");
        }
        return dataFinalizado;
    }

    public void diagnosticoResolvido() {
        this.status = VerificaDiagnostico.RESOLVIDO;
        dataFinalizado = LocalDate.now();
    }

    public Automovel getAutomovel() {
        return automovel;
    }

    public void setAutomovel(Automovel automovel) {
        this.automovel = automovel;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setDataFinalizado(LocalDate dataFinalizado) {
        this.dataFinalizado = dataFinalizado;
    }

    public void setStatus(VerificaDiagnostico status) {
        this.status = status;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataStr = sdf.format(data);
        if(status == VerificaDiagnostico.DIAGNOSTICO_NAO_RESOLVIDO){
            return "id: " + id +  ", descrição: " + descricao + ", data inicio: " + dataStr + ", status: " + status;
        }

        String dataFinalizadoStr = sdf.format(dataFinalizado);
        return "id: " + id
                + ", descrição: " + descricao
                + ", data inicio: " + dataStr
                + ", data finalizado: " + dataFinalizadoStr
                + ", status: " + status;
    }
}
