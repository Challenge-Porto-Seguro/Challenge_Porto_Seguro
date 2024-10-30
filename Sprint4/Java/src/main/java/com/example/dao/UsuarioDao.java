package com.example.dao;

import com.example.exceptions.UsuarioNotCreate;
import com.example.exceptions.UsuarioNotFound;
import com.example.model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UsuarioDao {

    void insertUsuario(Connection conn, Usuario usuario) throws UsuarioNotCreate, SQLException;
    void updateUsuario(Connection conn, Usuario usuario) throws UsuarioNotFound, SQLException;
    void deleteById(Connection conn, Long id) throws UsuarioNotFound, SQLException;
    Optional<Usuario> findById(Connection conn, Long id) throws SQLException;
    List<Usuario> findAll(Connection conn) throws SQLException;
}
