package com.example.db.implDao;

import com.example.db.DB;
import com.example.db.DbException;
import com.example.db.dao.AutomovelDao;
import com.example.model.Automovel;
import com.example.model.usuarios.Usuario;
import com.example.service.UsuarioService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AutomovelDaoJDBC implements AutomovelDao {

    private Connection conn;

    public AutomovelDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Automovel automovel) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("""
                insert into T_PS_AUTOMOVEL (cd_pessoa, nm_marca_veiculo, nm_modelo_veiculo, sq_placa, dt_veiculo)
                values (?, ?, ?, ?, ?)
            """, new String[] {"cd_automovel"});
            ps.setLong(1, automovel.getUsuario().getId());
            ps.setString(2, automovel.getMarca());
            ps.setString(3, automovel.getModelo());
            ps.setString(4, automovel.getPlaca());
            ps.setDate(5, new Date(automovel.getAno().getTime()));

            int rowsAfected = ps.executeUpdate();
            if (rowsAfected > 0) {
                rs = ps.getGeneratedKeys();
                if(rs.next()){
                    Long id = rs.getLong(1);
                    automovel.setId(id);
                }
            } else {
                throw new DbException("Erro ao inserir automovel");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void update(Automovel automovel) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("""
                update T_PS_AUTOMOVEL
                    set nm_marca_veiculo = ?, nm_modelo_veiculo = ?, sq_placa = ?, dt_veiculo = ?
                where cd_automovel = ?
            """);
            ps.setString(1, automovel.getMarca());
            ps.setString(2, automovel.getModelo());
            ps.setString(3, automovel.getPlaca());
            ps.setDate(4, new Date(automovel.getAno().getTime()));
            ps.setLong(5, automovel.getId());


            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }

    }

    @Override
    public void deleteById(long id) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("""
                delete from T_PS_AUTOMOVEL where cd_automovel = ?
            """);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    @Override
    public Optional<Automovel> findById(long id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("""
                select aut.*, usuario.sq_cpf, pessoa.* from T_PS_AUTOMOVEL aut
                    join T_PS_USUARIO usuario on aut.cd_pessoa = usuario.cd_pessoa
                    join T_PS_PESSOA pessoa on usuario.cd_pessoa = pessoa.cd_pessoa
                    where aut.cd_automovel = ?
            """);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                Automovel automovel = instanciaAutomovel(rs);
                return Optional.of(automovel);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Automovel> findAll() {
        List<Automovel> automoveis = new ArrayList<>();
        Statement statement = null;
        ResultSet rs = null;
        String sql = """ 
                    select aut.*, usuario.sq_cpf, pessoa.* from T_PS_AUTOMOVEL aut
                        join T_PS_USUARIO usuario on aut.cd_pessoa = usuario.cd_pessoa
                        join T_PS_PESSOA pessoa on usuario.cd_pessoa = pessoa.cd_pessoa
                        order by pessoa.nm_nome
        """;
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()){
                automoveis.add(instanciaAutomovel(rs));
            }
            return automoveis;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(rs);
        }
    }

    private Automovel instanciaAutomovel(ResultSet rs) throws SQLException {
        Automovel automovel = new Automovel(rs.getString("nm_marca_veiculo"), rs.getString("nm_modelo_veiculo"), rs.getString("sq_placa"), rs.getDate("dt_veiculo"), instaciaUsuario(rs));
        automovel.setId(rs.getLong("cd_automovel"));
        return automovel;
    }

    private Usuario instaciaUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario(rs.getString("nm_nome"), rs.getString("sq_cpf"), rs.getString("nm_email"), rs.getString("sq_senha"));
        usuario.setId(rs.getLong("cd_pessoa"));
        return usuario;
    }
}
