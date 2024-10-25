package com.example.service;

import com.example.exceptions.UsuarioNotCreate;
import com.example.exceptions.UsuarioNotDelete;
import com.example.exceptions.UsuarioNotFound;
import com.example.exceptions.UsuarioNotUpdate;
import com.example.model.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario login(String email, String senha);

    Usuario cadastraUsuario(Usuario usuario) throws UsuarioNotCreate;

    Usuario buscaUsuarioPorId(Long id) throws UsuarioNotFound;

    Usuario alteraUsuario(Usuario usuario) throws UsuarioNotFound, UsuarioNotUpdate;

    void excluiUsuario(Long id) throws UsuarioNotFound, UsuarioNotDelete;

    List<Usuario> listaUsuarios() throws UsuarioNotFound;
}
