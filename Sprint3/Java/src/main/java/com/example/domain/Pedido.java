package com.example.domain;

public class Pedido {

    private Long id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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
