package com.example.service;

import com.example.exceptions.ProdutoNotCreate;
import com.example.exceptions.ProdutoNotFound;
import com.example.model.Produto;

import java.sql.Connection;
import java.sql.SQLException;

public interface ProdutoService {

    void insert(Connection connection, Produto produto) throws SQLException, ProdutoNotCreate, ProdutoNotFound;
    Produto selectByNome(String name) throws SQLException, ProdutoNotFound;
    Produto selectById(Long id) throws SQLException, ProdutoNotFound;
}
