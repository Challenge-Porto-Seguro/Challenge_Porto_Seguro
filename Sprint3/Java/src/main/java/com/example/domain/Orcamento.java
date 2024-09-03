package com.example.domain;

import com.example.enums.StatusOrcamento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Orcamento {

    private Long id;
    private double valorTotal;
    private LocalDate diaOrcamento;
    private List<Pedido> pedidos = new ArrayList<>();
    private StatusOrcamento statusOrcamento;

    public Orcamento() {
        this.statusOrcamento = StatusOrcamento.ATIVO;
        diaOrcamento = LocalDate.now();
    }

    public double getValorTotal() {
        double total = 0;
        for (Pedido pedido : pedidos) {
            total += pedido.getValorTotal();
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

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void removePedido(Pedido pedido) {
        pedidos.remove(pedido);
    }

    public StatusOrcamento getStatus() {
        return statusOrcamento;
    }

    public void setStatus(StatusOrcamento statusOrcamento) {
        this.statusOrcamento = statusOrcamento;
    }

    public LocalDate getDiaOrcamento() {
        return diaOrcamento;
    }

    @Override
    public String toString() {
        return "Orcamento{" +
                "id = " + id +
                ", valorTotal = " + getValorTotal() +
                ", pedidos = " + pedidos +
                ", statusOrcamento = " + statusOrcamento +
                '}';
    }
}
