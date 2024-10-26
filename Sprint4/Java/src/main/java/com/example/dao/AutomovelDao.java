package com.example.dao;

import com.example.exceptions.AutomovelInvalido;
import com.example.exceptions.AutomovelNotCreate;
import com.example.exceptions.AutomovelNotFound;
import com.example.model.Automovel;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface AutomovelDao {

    void insert(Connection conn, Automovel automovel) throws AutomovelNotCreate, AutomovelInvalido;
    void update(Connection conn, Automovel automovel) throws AutomovelNotFound;
    void deleteById(Connection conn, long id) throws AutomovelNotFound;
    Optional<Automovel> findById(Connection conn, long id);
    List<Automovel> findAll(Connection conn, Long idUsuario);
}
