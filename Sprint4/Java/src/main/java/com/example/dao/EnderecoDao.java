package com.example.dao;

import com.example.exceptions.EnderecoNotFound;
import com.example.model.Endereco;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface EnderecoDao {

    void insertEndereco(Connection conn, Endereco endereco) throws SQLException;
    void updateEndereco(Connection conn, Endereco endereco) throws EnderecoNotFound, SQLException;
    void deleteById(Connection conn, Long id) throws EnderecoNotFound, SQLException;
    Optional<Endereco> findById(Connection conn, Long id) throws SQLException;
}
