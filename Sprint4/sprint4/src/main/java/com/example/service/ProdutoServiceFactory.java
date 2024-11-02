package com.example.service;

public final class ProdutoServiceFactory {

    private ProdutoServiceFactory() {
        throw new UnsupportedOperationException("Essa classe não deve ser instanciado");
    }

    public static ProdutoService getProdutoService() {
        return new ProdutoServiceImpl();
    }
}
