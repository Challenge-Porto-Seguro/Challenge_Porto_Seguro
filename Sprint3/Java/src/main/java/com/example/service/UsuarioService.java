package com.example.service;

import com.example.db.dao.DaoFactory;
import com.example.db.dao.UsuarioDao;
import com.example.model.usuarios.Usuario;

public class UsuarioService {

    private final UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();

    public void cadastraUsuario(Usuario usuario) {
        usuarioDao.insertUsuario(usuario);
    }

    public Usuario buscaUsuarioPorId(Long id) {
        return usuarioDao.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }
}
