package com.example.impl;

import com.example.dao.AutomovelDao;
import com.example.db.DB;
import com.example.db.DbException;
import com.example.domain.Automovel;

import java.sql.*;
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
                insert into automoveis (marca, modelo, placa, ano) values (?, ?, ?, ?)
            """, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, automovel.getMarca());
            ps.setString(2, automovel.getModelo());
            ps.setString(3, automovel.getPlaca());
            ps.setDate(4, new Date(automovel.getAno().getTime()));

            int rowsAfected = ps.executeUpdate();
            if (rowsAfected > 0) {
                rs = ps.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
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
                update automoveis set (marca, modelo, placa , ano) values (?, ?, ?, ?) where id = ?
            """);
            if (automovel.getMarca() != null) {
                ps.setString(1, automovel.getMarca());
            }
            if (automovel.getModelo() != null) {
                ps.setString(2, automovel.getModelo());
            }
            if (automovel.getPlaca() != null) {
                ps.setString(3, automovel.getPlaca());
            }
            if (automovel.getAno() != null) {
                ps.setDate(4, new Date(automovel.getAno().getTime()));
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }

    }

    @Override
    public void deleteById(int id) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("""
                delete from automoveis where id = ? 
            """);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    @Override
    public Optional<Automovel> findById(int id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("""
                select * from automoveis where id = ?
            """);
            ps.setInt(1, id);
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

    private Automovel instanciaAutomovel(ResultSet rs) throws SQLException {
        Automovel automovel = new Automovel(rs.getString("marca"), rs.getString("modelo"), rs.getString("placa"), rs.getDate("ano"));
        automovel.setId(rs.getInt("id"));
        return automovel;
    }
}
