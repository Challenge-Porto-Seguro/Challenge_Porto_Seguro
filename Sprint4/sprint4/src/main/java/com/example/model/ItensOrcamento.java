package com.example.model;

public class ItensOrcamento {

    private PKItensOrcamento id = new PKItensOrcamento();
    private int quantidade;
    private double valorTotal;

    public ItensOrcamento(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorTotal() {
        valorTotal = id.getProduto().getPreco() * quantidade;
        return valorTotal;
    }


    public PKItensOrcamento getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Pedido: " + id.getIdProduto() + ", Quantidade: " + quantidade + ", Valor Total: " + getValorTotal();
    }
}
