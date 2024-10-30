package com.example.dao;


import com.example.exceptions.UsuarioNotCreate;
import com.example.exceptions.UsuarioNotFound;
import com.example.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

final class UsuarioDaoImpl implements UsuarioDao {

    @Override
    public void insertUsuario(Connection conn, Usuario usuario) throws UsuarioNotCreate, SQLException {
        String sql = """
                insert into T_PS_USUARIO(cd_pessoa, sq_cpf)
                values (?, ?)
            """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, usuario.getId());
            ps.setString(2, usuario.getCpf());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new UsuarioNotCreate();
            }
        }
    }


    @Override
    public void updateUsuario(Connection conn, Usuario usuario) throws UsuarioNotFound, SQLException {
        String sql = """
                update T_PS_USUARIO set sq_cpf = ? where cd_pessoa = ?
            """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getCpf());
            ps.setLong(2, usuario.getId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new UsuarioNotFound();
            }
        }
    }


    @Override
    public void deleteById(Connection conn, Long id) throws UsuarioNotFound, SQLException {
        String sql = """
            delete from T_PS_USUARIO where cd_pessoa = ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new UsuarioNotFound();
            }
        }
    }


    @Override
    public Optional<Usuario> findById(Connection conn, Long id) throws SQLException {
        String sql = """
            select usuario.sq_cpf, login.* from T_PS_USUARIO usuario
            join T_PS_PESSOA login on usuario.cd_pessoa = login.cd_pessoa
            where usuario.cd_pessoa = ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(instaciaUsuario(rs));
                }
            }
        }
        return Optional.empty();
    }


    @Override
    public List<Usuario> findAll(Connection conn) throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = """
            select usuario.sq_cpf, pessoa.* from T_PS_USUARIO usuario
            join T_PS_PESSOA pessoa on usuario.cd_pessoa = pessoa.cd_pessoa
            order by nm_nome
        """;

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                usuarios.add(instaciaUsuario(rs));
            }
        }
        return usuarios;
    }


    private Usuario instaciaUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario(rs.getString("nm_nome"), rs.getString("sq_cpf"), rs.getString("nm_email"), rs.getString("sq_senha"));
        usuario.setId(rs.getLong("cd_pessoa"));
        return usuario;
    }
}
