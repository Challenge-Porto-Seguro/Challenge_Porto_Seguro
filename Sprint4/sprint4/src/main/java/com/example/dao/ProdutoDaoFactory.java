package com.example.dao;

public class ProdutoDaoFactory {

    private ProdutoDaoFactory(){
        throw new UnsupportedOperationException("Essa classe não deve ser instanciada");
    }

    public static ProdutoDao getProdutoDao(){
        return new ProdutoDaoImpl();
    }
}
