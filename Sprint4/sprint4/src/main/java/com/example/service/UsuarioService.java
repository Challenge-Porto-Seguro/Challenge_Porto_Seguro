package com.example.service;

import com.example.exceptions.*;
import com.example.model.Endereco;
import com.example.model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UsuarioService {

    Usuario cadastraUsuario(Usuario usuario, Endereco endereco) throws UsuarioNotCreate, SQLException, LoginNotCreate, CadastroInvalido, EnderecoNotCreate;

    Usuario buscaUsuarioPorId(Long id) throws UsuarioNotFound, SQLException;

    Usuario alteraUsuario(Usuario usuario, Endereco endereco) throws UsuarioNotFound, UsuarioNotUpdate, SQLException, LoginNotUpdade, LoginNotFound, EnderecoNotUpdate, EnderecoNotFound;

    void excluiUsuario(Long id) throws UsuarioNotFound, UsuarioNotDelete, SQLException, EnderecoNotFound, EnderecoNotDelete;

    List<Usuario> listaUsuarios() throws SQLException;
}
