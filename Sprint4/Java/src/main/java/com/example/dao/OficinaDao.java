package com.example.dao;

import com.example.exceptions.OficinaNotFound;
import com.example.model.Oficina;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface OficinaDao {

    void insertOficina(Connection conn, Oficina oficina) throws SQLException;
    void updateOficina(Connection conn, Oficina oficina) throws OficinaNotFound, SQLException;
    void deleteById(Connection conn, Long id) throws OficinaNotFound, SQLException;
    Optional<Oficina> findById(Connection conn, Long id) throws SQLException;
    List<Oficina> findAll(Connection conn) throws SQLException;
}
