package com.example.db.implDao;

import com.example.db.DB;
import com.example.db.DbException;
import com.example.db.dao.UsuarioDao;
import com.example.model.usuarios.Usuario;

import java.sql.*;
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
                    insert into T_PS_LOGIN(nm_nome, nm_email, sq_senha)
                    values (?, ?, ?)
                """, new String[] {"cd_login"});

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
                    insert into T_PS_USUARIO(cd_login, sq_cpf, qt_orcamento_mes)
                    values (?, ?, ?)
                """);
                ps.setLong(1, usuario.getId());
                ps.setString(2, usuario.getCpf());
                ps.setInt(3, 3);
                int rowsAffected2 = ps.executeUpdate();
                if (rowsAffected2 > 0) {
                    conn.commit();
                } else {
                    conn.rollback();
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
                update usuarios set nome = ?, email = ?, senha = ?, cpf = ?, quantidade_orcamento = ? where id = ?
            """);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getCpf());
            ps.setInt(5, usuario.getQuantidadeOrcamento());
            ps.setLong(6, usuario.getId());
            ps.executeUpdate();
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
                delete from usuarios where id = ?
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
                select * from T_PS_USUARIO usuario join T_PS_LOGIN login on usuario.cd_login = login.cd_login where usuario.cd_login = ?
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

    private Usuario instaciaUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario(rs.getString("nm_nome"), rs.getString("sq_cpf"), rs.getString("nm_email"), rs.getString("sq_senha"));
        usuario.setId(rs.getLong("cd_login"));
        return usuario;
    }
}
