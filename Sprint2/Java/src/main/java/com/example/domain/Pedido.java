package com.example.domain;

public class Pedido {

    private int quantidade;
    private double valor;
    private Produto produto;

    public Pedido(int quantidade, Produto produto) {
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValor() {
        return valor;
    }

    public Produto getProduto() {
        return produto;
    }

    public double setValor() {
        return quantidade * produto.getPreco();
    }
}
