package com.example.dao;

import com.example.model.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface ProdutoDao {

    void insert(Connection connection, Produto produto) throws SQLException;
    Optional<Produto> selectByNome(Connection connection, String name) throws SQLException;
    Optional<Produto> selectById(Connection connection, Long id) throws SQLException;
}
