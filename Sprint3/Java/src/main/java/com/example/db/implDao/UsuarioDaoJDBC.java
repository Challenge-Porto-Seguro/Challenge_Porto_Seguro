package com.example.db.implDao;

import com.example.db.DB;
import com.example.db.DbException;
import com.example.db.dao.UsuarioDao;
import com.example.model.usuarios.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioDaoJDBC implements UsuarioDao {

    private final Connection conn;

    public UsuarioDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertUsuario(Usuario usuario) {
        PreparedStatement ps = null;

        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(""" 
                    insert into T_PS_PESSOA(nm_nome, nm_email, sq_senha)
                    values (?, ?, ?)
                """, new String[] {"cd_pessoa"});


            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    usuario.setId(id);
                }
                DB.closeResultSet(rs);
                ps = conn.prepareStatement("""
                    insert into T_PS_USUARIO(cd_pessoa, sq_cpf)
                    values (?, ?)
                """);
                ps.setLong(1, usuario.getId());
                ps.setString(2, usuario.getCpf());
                int rowsAffected2 = ps.executeUpdate();
                if (rowsAffected2 > 0) {
                    conn.commit();
                    conn.setAutoCommit(true);
                } else {
                    conn.rollback();
                    conn.setAutoCommit(true);
                    throw new DbException("Erro ao inserir usuario");
                }
            } else {
                throw new DbException("Erro ao inserir usuario");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }

    }

    @Override
    public void updateUsuario(Usuario usuario) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("""
                update T_PS_PESSOA set nm_nome = ?,nm_email = ?, sq_senha = ? where cd_pessoa = ?
            """);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setLong(4, usuario.getId());
            ps.executeUpdate();
            ps = conn.prepareStatement("""
                update T_PS_USUARIO set sq_cpf = ? where cd_pessoa = ?
            """);
            ps.setString(1, usuario.getCpf());
            ps.setLong(2, usuario.getId());
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void deleteById(Long id) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("""
                delete from T_PS_USUARIO where cd_pessoa = ?
            """);

            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("""
                select usuario.sq_cpf, login.* from T_PS_USUARIO usuario join T_PS_PESSOA login on usuario.cd_pessoa = login.cd_pessoa where usuario.cd_pessoa = ?
            """);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return Optional.of(instaciaUsuario(rs));
            }
            DB.closeResultSet(rs);
            return Optional.empty();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }

    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select usario.sq_cpf, pessoa.*  from T_PS_USUARIO usario join T_PS_PESSOA pessoa on usario.cd_pessoa = pessoa.cd_pessoa";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                usuarios.add(instaciaUsuario(rs));
            }
            return usuarios;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(stmt);
        }
    }

    private Usuario instaciaUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario(rs.getString("nm_nome"), rs.getString("sq_cpf"), rs.getString("nm_email"), rs.getString("sq_senha"));
        usuario.setId(rs.getLong("cd_pessoa"));
        return usuario;
    }
}
