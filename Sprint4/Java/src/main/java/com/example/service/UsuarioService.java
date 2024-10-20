package com.example.service;

import com.example.db.dao.DaoFactory;
import com.example.db.dao.UsuarioDao;
import com.example.model.usuarios.Usuario;

import java.util.List;

public class UsuarioService {

    private final UsuarioDao repository = DaoFactory.createUsuarioDao();

    public void cadastraUsuario(Usuario usuario) {
        repository.insertUsuario(usuario);
    }

    public Usuario buscaUsuarioPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    public void alteraUsuario(Usuario usuario) {
        repository.updateUsuario(usuario);
    }
    public void excluiUsuario(Long id) {
        repository.deleteById(id);
    }

    public List<Usuario> listaUsuarios() {
        return repository.findAll();
    }
}
