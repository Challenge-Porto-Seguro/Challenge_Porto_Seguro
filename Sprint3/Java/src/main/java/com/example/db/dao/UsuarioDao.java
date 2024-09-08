package com.example.db.dao;

import com.example.model.usuarios.Usuario;

import java.util.Optional;

public interface UsuarioDao {

    void insertUsuario(Usuario usuario);
    void updateUsuario(Usuario usuario);
    void deleteById(Long id);
    Optional<Usuario> findById(Long id);
}
