package com.example.dao;

import com.example.exceptions.AutomovelNotCreate;
import com.example.exceptions.AutomovelNotFound;
import com.example.model.Automovel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AutomovelDao {

    void insert(Connection conn, Automovel automovel) throws AutomovelNotCreate, SQLException;
    void update(Connection conn, Automovel automovel) throws AutomovelNotFound, SQLException;
    void deleteById(Connection conn, long id) throws AutomovelNotFound, SQLException;
    Optional<Automovel> findById(Connection conn, long id) throws SQLException;
    List<Automovel> findAll(Connection conn, Long idUsuario) throws SQLException;
}
