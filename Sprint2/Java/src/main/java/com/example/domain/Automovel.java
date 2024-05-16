package com.example.domain;

import com.example.enums.VerificaDiagnostico;

import java.util.Date;
import java.util.List;

public class Automovel {

    private String marca;
    private String modelo;
    private String placa;
    private Date ano;
    private Diagnostico diagnostico;
    private List<Diagnostico> diagnosticos;

    public Automovel(String marca, String modelo, String placa, Date ano) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.ano = ano;
    }

    private boolean verificaSeAutomovelEValido(){
        return true;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public Date getAno() {
        return ano;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Diagnostico getDiagnostico(){
        return diagnostico;
    }

    public List<Diagnostico> getDiagnosticos() {
        return diagnosticos;
    }

    public void autalizarDiagnostico(){
        if(diagnostico.getVerificador() == VerificaDiagnostico.RESOLVIDO){
            diagnosticos.add(diagnostico);
            diagnostico = null;
        }
    }
}
