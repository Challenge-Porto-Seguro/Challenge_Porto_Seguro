package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Orcamento {

    private Long id;
    private Double valorTotal;
    private Diagnostico diagnostico;
    private List<ItensOrcamento> itensOrcamentos = new ArrayList<>();
    private StatusOrcamento statusOrcamento;

    public Orcamento(StatusOrcamento statusOrcamento, Diagnostico diagnostico) {
        this.statusOrcamento = statusOrcamento;
        this.diagnostico = diagnostico;
    }

    public double getValorTotal() {
        double total = 0;
        for (ItensOrcamento itensOrcamento : itensOrcamentos) {
            total += itensOrcamento.getValorTotal();
        }

        this.valorTotal = total;
        return valorTotal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<ItensOrcamento> getPedidos() {
        return itensOrcamentos;
    }

    public void addPedido(ItensOrcamento itensOrcamento) {
        itensOrcamentos.add(itensOrcamento);
    }

    public void removePedido(ItensOrcamento itensOrcamento) {
        itensOrcamentos.remove(itensOrcamento);
    }

    public StatusOrcamento getStatus() {
        return statusOrcamento;
    }

    public void setStatus(StatusOrcamento statusOrcamento) {
        this.statusOrcamento = statusOrcamento;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setPedidos(List<ItensOrcamento> itensOrcamentos) {
        this.itensOrcamentos = itensOrcamentos;
    }

    public StatusOrcamento getStatusOrcamento() {
        return statusOrcamento;
    }

    @Override
    public String toString() {
        return "Orcamento{" +
                "id = " + id +
                ", valorTotal = " + getValorTotal() +
                ", pedidos = " + itensOrcamentos +
                ", statusOrcamento = " + statusOrcamento +
                ", diagnostico = " + diagnostico +
                ", statusOrcamento = " + statusOrcamento +
                '}';
    }
}
