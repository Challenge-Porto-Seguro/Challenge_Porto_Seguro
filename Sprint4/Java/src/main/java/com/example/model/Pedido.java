package com.example.model;

public class Pedido {

    private PkPedido id = new PkPedido();
    private int quantidade;
    private double valorTotal;
    private Produto produto;

    public Pedido(int quantidade, Produto produto) {
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorTotal() {
        valorTotal = produto.getPreco() * quantidade;
        return valorTotal;
    }

    public Produto getProduto() {
        return produto;
    }

    @Override
    public String toString() {
        return "Pedido: " + produto + ", Quantidade: " + quantidade + ", Valor Total: " + getValorTotal();
    }
}
