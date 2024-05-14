package com.example.domain;

import com.example.enums.StatusOrcamento;

import java.util.ArrayList;
import java.util.List;

public class Orcamento {

    private static int sequencia = 1;
    private int id;
    private double valorTotal;
    private List<Pedido> pedidos = new ArrayList<>();
    private StatusOrcamento statusOrcamento;

    public Orcamento() {
        this.statusOrcamento = StatusOrcamento.ATIVO;
        id += sequencia++;
    }

    public double getValorTotal() {
        double total = 0;
        for (Pedido pedido : pedidos) {
            total += pedido.getValorTotal();
        }

        this.valorTotal = total;
        return valorTotal;
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
