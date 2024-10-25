package com.example.model;

import com.example.validacoes.VerificaCriacaoAutomovel;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Automovel {

    private Long id;
    private Usuario usuario;
    private String marca;
    private String modelo;
    private String placa;
    private LocalDate ano;
    private Diagnostico diagnostico;
    private List<Diagnostico> diagnosticos = new ArrayList<>();

    public Automovel(String marca, String modelo, String placa, LocalDate ano, Usuario usuario) {
        VerificaCriacaoAutomovel.verifica(marca, modelo, placa, ano);
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.ano = ano;
        this.usuario = usuario;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
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

    public LocalDate getAno() {
        return ano;
    }

    public Long getId() {
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
                ", usuario=" + usuario + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", placa='" + placa + '\'' +
                ", ano=" + ano +
                ", diagnostico=" + diagnostico +
                ", diagnosticos=" + diagnosticos +
                '}';
    }
}
