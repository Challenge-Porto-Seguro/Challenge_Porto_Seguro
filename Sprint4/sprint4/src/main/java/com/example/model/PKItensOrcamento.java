package com.example.model;

public class PKItensOrcamento {

    private Produto produto;
    private Orcamento orcamento;


    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public Produto getProduto() {
        return produto;
    }

    public Long getIdOrcamento() {
        return orcamento.getId();
    }

    public Long getIdProduto() {
        return produto.getId();
    }
}
