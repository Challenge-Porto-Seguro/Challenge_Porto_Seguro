package com.example.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Automovel {

    private int id;
    private String marca;
    private String modelo;
    private String placa;
    private Date ano;
    private Diagnostico diagnostico;
    private List<Diagnostico> diagnosticos = new ArrayList<>();

    public Automovel(String marca, String modelo, String placa, Date ano) {
        verificaSeAutomovelEValido(marca, modelo, placa, ano);
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.ano = ano;
    }

    private void verificaSeAutomovelEValido(String marca, String modelo, String placa, Date ano){
        if(marca.isBlank()){
            throw new RuntimeException("Marca invalida");
        }
        if(modelo.isBlank()){
            throw new RuntimeException("Modelo invalido");
        }
        if(placa.isBlank() || !placa.matches("[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")){
            throw new RuntimeException("Placa invalida");
        }
        if(ano == null || ano.after(new Date())){
            throw new RuntimeException("Ano invalido");
        }
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public String anoFormatado(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(ano);
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
        diagnostico.diagnosticoResolvido();
        diagnosticos.add(diagnostico);
        diagnostico = null;
    }

    @Override
    public String toString() {
        return "Automovel{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", placa='" + placa + '\'' +
                ", ano=" + ano +
                ", diagnostico=" + diagnostico +
                ", diagnosticos=" + diagnosticos +
                '}';
    }
}
